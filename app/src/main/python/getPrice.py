import requests
from bs4 import BeautifulSoup
def getPrice(code):
    code = "{0:0>6}".format(code)
    print(code)
    url=f"http://stock.hankyung.com/apps/search.result?keyword={int(code)}"
    res = requests.get(url)
    res.raise_for_status()
    soup = BeautifulSoup(res.text,"lxml")
    tbody = soup.find("tbody",attrs={"id":"contentsList"})
    tds = tbody.findAll("td",attrs={"class":"num_c"})
    price = tds[0].get_text()
    return price