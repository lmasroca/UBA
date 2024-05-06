import logging
logging.getLogger("scapy.runtime").setLevel(logging.ERROR)
from scapy.all import *
from time import *
from statistics import mode
import pandas as pd
import sys
import requests
import socket
from tqdm import tqdm

IP_pub_destino = ""
IP_pub_fuente = ""

def traceroute(url, iterations, max_ttl):
    global IP_pub_destino, IP_pub_fuente
    IP_pub_destino = socket.gethostbyname(url)
    IP_pub_fuente = requests.get('https://api.ipify.org').text # usamos esto para conseguir nuestra ip publica para el mapa, ya que la primer direccion ip que aparece sera la ip privada del gateway
    ips = {}
    rtts = {}
    df = pd.DataFrame(columns=['ttl', 'ip', 'rttHop', 'rttTot', 'lat', 'lon', 'outlier'])

    for i in range(1,max_ttl):
        ips[i] = []
        rtts[i] = []

    for i in tqdm(range(iterations)):
        for ttl in range(1,max_ttl):
            probe = IP(dst=url, ttl=ttl) / ICMP()
            t_i = time()
            ans = sr1(probe, verbose=False, timeout=0.8)
            t_f = time()
            rtt = (t_f - t_i)*1000
            if ans is not None:
                ips[ttl].append(ans.src)
                rtts[ttl].append(rtt)
                if ans.src == IP_pub_destino:
                    break

    prev_rtt_mean = 0
    for i in range(1,max_ttl):
        ipps = ips[i]
        rts = rtts[i]
        if len(ipps) > 0:
            most_common_ip = mode(ipps)
            rtt_mean = sum(rts) / len(rts)
            df = pd.concat([df, pd.DataFrame({'ttl': i, 'ip': most_common_ip if i > 1 else IP_pub_fuente, 'rttHop': rtt_mean - prev_rtt_mean, 'rttTot': rtt_mean, 'lat': 0, 'lon': 0, 'outlier': False}, index = [0])], ignore_index=True)
            prev_rtt_mean = rtt_mean
            if most_common_ip == IP_pub_destino:
                break

    return df