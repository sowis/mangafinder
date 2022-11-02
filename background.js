const contextMenuItem = {
    "id": "sauceNAO",
    "title": "search by sauceNAO",
    "contexts": ["image"],
    "documentUrlPatterns":["https://gall.dcinside.com/*"]
};

chrome.contextMenus.create(contextMenuItem);

chrome.contextMenus.onClicked.addListener(async function(clickData) {
    chrome.tabs.query({
        "active":true,
        "currentWindow":true
    }, tabs => {
        chrome.tabs.sendMessage(tabs[0].id, {
            "clicked":true,
            "clickData":clickData
        });
    });
});