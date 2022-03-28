import requests
from flask_cors import CORS
from flask import Flask
from flask import request
import ssl

session = requests.Session()

app = Flask(__name__)
CORS(app)

@app.route("/", methods=['GET'])
def data():
    srcUrl = request.args.get('srcUrl')
    pageUrl = request.args.get('pageUrl')

    url_query = {}
    for q in pageUrl.split('?')[1].split('&'):
        url_query[q.split('=')[0]] = q.split('=')[1]

    mobile_url = "https://m.dcinside.com/board/" + url_query["id"] + "/" + url_query["no"]

    headers = {'User-Agent':'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Mobile Safari/537.36',
        'Accept':'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9',
        'Referer': mobile_url}

    req = session.get(srcUrl, headers = headers, timeout = 5)
    return req.content

if __name__ == "__main__":
    ssl_context = ssl.SSLContext(ssl.PROTOCOL_TLS)
    ssl_context.load_cert_chain(certfile='server.crt', keyfile='server.key', password='parkpark1')
    app.run(host="0.0.0.0", port=6974, ssl_context=ssl_context, debug=False)