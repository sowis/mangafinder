const server_ip_adderss = '192.168.0.10:6974'

let clickedElement = null;

document.addEventListener('contextmenu', event => {
    clickedElement = event.target;
});

function search(blob) {
    let form = document.createElement("form");
    form.setAttribute("id", "searchForm");
    form.setAttribute("method", "post");
    form.setAttribute("action", "https://saucenao.com/search.php");
    form.setAttribute("encType", "multipart/form-data");
    form.setAttribute("target", "_blank");

    let fileInput = document.createElement("input");
    fileInput.setAttribute("type", "file");
    fileInput.setAttribute("name", "file");

    let safeSearch = document.createElement("input");
    safeSearch.setAttribute("type", "checkbox");
    safeSearch.setAttribute("name", "hide");
    safeSearch.setAttribute("value", "3");
    safeSearch.setAttribute("id", "safe-cb");

    let auto = document.createElement("input");
    auto.setAttribute("type", "checkbox");
    auto.setAttribute("id", "auto-cb");

    let urlInput = document.createElement("input");
    urlInput.setAttribute("type", "text");
    urlInput.setAttribute("id", "urlInput");
    urlInput.setAttribute("name", "url");

    let submit = document.createElement("input");
    submit.setAttribute("type", "submit");
    submit.setAttribute("value", "SEARCH");
    submit.setAttribute("id", "searchButton");

    let file = new File([blob], "img.png", {type:"image/png"});
    let container = new DataTransfer();
    container.items.add(file);
    fileInput.files = container.files;

    form.appendChild(fileInput);
    form.appendChild(safeSearch);
    form.appendChild(auto);
    form.appendChild(urlInput);
    form.appendChild(submit);

    document.body.appendChild(form);
    form.submit();
}

chrome.runtime.onMessage.addListener(async function(message) {
    if (message.clicked != true) {
        return;
    }
    
    const clickData = message.clickData;
    const response = await fetch('https://' + server_ip_adderss + '/?srcUrl=' + encodeURIComponent(clickData.srcUrl) + '&pageUrl=' + encodeURIComponent(clickData.pageUrl));
    const blob = await response.blob();
    search(blob);
});