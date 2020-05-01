var value=window.songId;
var mydata;
function debounce(func, time, flag) {
            var timer = null;
            var debounced = function () {
                var _this = this;
                var argu = arguments;
                clearTimeout(timer);
                if(flag) {
                    if(!timer) func.apply(_this, argu);
                    timer = setTimeout(function () {
                        timer = null;
                    }, time)
                } else {
                    timer = setTimeout(function () {
                        func.apply(_this, argu);
                    }, time)
                }  
            }
            debounced.cancel = function () {
                clearTimeout(timer);
                timer = null;
            }
            return debounced;
        }
function setinput(e){
	 value = this.value;
	 console.log(value);
	 search(value);
	}
function search(value){
	$.ajax({
	url: 'https://api.douban.com/v2/music/'+value,
	type: 'GET',
	dataType: 'jsonp',
	// data: value,
	success:function (data){
		mydata=data;
		console.log(data);
		 createDom(mydata);
		}
     });
}
function createDom(mydata){
	var str='';
	var ele=mydata.rating.average;
	if(!mydata.attrs.singer){
		mydata.singer ="暂无";
	}
	 	str+=	'<h1>'+mydata.title+'</h1>\
		<div class="left-img">\
			<img src="'+mydata.image+'" alt="">\
		</div>\
		<div class="right-text">\
			<p>又名:'+mydata.alt_title+' </p>\
			<p>表演者: '+mydata.attrs.singer+' </p>\
			<p>介质: '+mydata.attrs.media+'</p>\
			<p>发行时间:'+mydata.attrs.pubdate+' </p>\
			<p>出版者: '+mydata.attrs.publisher+' </p>\
		</div>\
		<div class="scorebox">\
			<p>豆瓣评分</p>\
			<p class="score">'+ele+'<span></span></p>\
		</div>\
		<div class="footer">\
			<li><span></span>写短评</li>\
			<li><span></span>写乐评</li>\
		</div>';
   $('.content').html(str);
    ele=-15*(10-parseInt(ele));
	$('.scorebox span').css('background-position','0px '+ele+'px');
}
$('.left-img span').css('background-position','0px -150px' );
search(value);
$('.btn').on('click',function(){
    value =$('.inp').val();
    window.location.href='/music/searchInfo'+'?value='+ value+'&start='+ 0;
})