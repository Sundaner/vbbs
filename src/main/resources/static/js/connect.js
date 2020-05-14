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
            notify(evt.data);
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
function notify(content) {
    toastr.options = { // toastr配置
        "closeButton": true, //是否显示关闭按钮
        "debug": false, //是否使用debug模式
        "progressBar": false, //是否显示进度条，当为false时候不显示；当为true时候，显示进度条，当进度条缩短到0时候，消息通知弹窗消失
        "positionClass": "toast-top-right",//显示的动画时间
        "showDuration": "400", //显示的动画时间
        "hideDuration": "1000", //消失的动画时间
        "timeOut": "6000", //展现时间
        "extendedTimeOut": "1000", //加长展示时间
        "showEasing": "swing", //显示时的动画缓冲方式
        "hideEasing": "linear", //消失时的动画缓冲方式
        "showMethod": "fadeIn", //显示时的动画方式
        "hideMethod": "fadeOut" //消失时的动画方式
    }
    toastr.info(content);
}