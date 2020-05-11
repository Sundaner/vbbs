function connect(userId){
    if(userId != null){
        if ('WebSocket' in window){
            ws = new WebSocket("ws://127.0.0.1:8080/socketServer/"+ userId);
        }
        else if ('MozWebSocket' in window){
            ws = new MozWebSocket("ws://127.0.0.1:8080/socketServer/"+ userId);
        }
        else{
            alert("该浏览器不支持websocket");
        }
        ws.onmessage = function(evt) {
            console.log(evt.data);
        };

        ws.onclose = function(evt) {
            console.log('connect closed')
        };

        ws.onopen = function(evt) {
            console.log("connect open")
        };
    }
}