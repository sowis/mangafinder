from http.server import HTTPServer, BaseHTTPRequestHandler
import requests
from urllib import parse

session = requests.Session()

class MyHandler(BaseHTTPRequestHandler):
    def do_GET(self):
        querys = {}
        if '?' in self.path:
            qs = self.path.split('?')[1].split('&')
            for query in qs:
                sp = query.split('=')
                querys[parse.unquote(sp[0])] = parse.unquote(sp[1])

        if "srcUrl" not in querys:
            self.send_response_only(403)
            self.send_header('Content-Type', 'text/plan')
            self.end_headers()
            self.wfile.write("forbidden")

        if "pageUrl" not in querys:
            self.send_response_only(403)
            self.send_header('Content-Type', 'text/plan')
            self.end_headers()
            self.wfile.write("forbidden")

        url_query = {}
        for q in querys['pageUrl'].split('?')[1].split('&'):
            url_query[q.split('=')[0]] = q.split('=')[1]

        mobile_url = "https://m.dcinside.com/board/" + url_query["id"] + "/" + url_query["no"]

        headers = {'User-Agent':'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Mobile Safari/537.36',
        'Accept':'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9',
        'Referer': mobile_url}

        req = session.get(querys['srcUrl'], headers = headers, timeout = 5)
        
        self.send_response_only(200)
        self.send_header('Content-Type', 'image/png')
        self.send_header('Access-Control-Allow-Origin', '*')
        self.end_headers()
        self.wfile.write(req.content)

        req.close()
        

if __name__ =='__main__':
    server = HTTPServer(('', 6974), MyHandler)
    print("mangafinder server on")
    server.serve_forever()