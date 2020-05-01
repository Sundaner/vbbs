var value=location.search.slice(2);
var scodevalue = window.key;
var spage = window.start/15;
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
function search(value,spage){
	$.ajax({
	url: 'https://api.douban.com/v2/music/search',
	type: 'GET',
	dataType: 'jsonp',
	data: '&q='+value+'&start='+spage*15,
	success:function (data){
		mydata=data.musics;
		console.log(data);
		 createDom(mydata);
		 var total =parseInt(data.total/15);
		 console.log(total);
		  page({id: 'div1', nowNum: spage, allNum: total});
		}
     });
}
function createDom(mydata){
	var str='';
	var score=[];
	for (var j =0 ;j<15;j++){
		var stdata=mydata[j];
		console.log(mydata[j]);
	 	str+='<li class="'+stdata.id+'">\
				<img src="'+stdata.image+'" height="200" width="200" alt="">\
				<p><a href="#">'+stdata.title+'</a></p>\
				<p><span></span>&nbsp'+stdata.rating.average+'('+stdata.rating.numRaters+'人评价)</p>\
				<p>'+stdata.attrs.singer+' / '+stdata.attrs.pubdate+'/ '+stdata.attrs.version+' / '+stdata.attrs.media+'</p>\
			</li>';
		score[j]=stdata.rating.average;
	}
	$('.inp').val(scodevalue);
	$('.content h1').html('搜索&nbsp'+scodevalue);
	$('.content-boxers').html(str);
	console.log(score);
	score.forEach(function(ele,index){
        var sele=-11*(10-parseInt(ele));
			$('.content-boxers span').eq(index).css('background-position','0px '+sele+'px' )
		

	})
}
if (value) {
	search(scodevalue,spage);
};
$('.btn').on('click',function(){
    scodevalue =$('.inp').val();
    window.location.href='/music/searchInfo'+'?value='+scodevalue+'&start=0';
})
$('.content-boxers').on('click','li',function(){
    console.log($(this).attr('class'));
    window.location.href='/music/search'+'?id='+$(this).attr('class');
})
 function page(json) {
            if (!json.id) return false;
            var $Div = document.getElementById(json.id);            
            var nowNum = json.nowNum;
            var allNum = json.allNum;
           
            // 首页
            if (nowNum > 5 && allNum >= 10) {
                var $A = document.createElement('a');
                $A.href = '/music/searchInfo'+'?value='+scodevalue+'&start=0';
                $A.innerHTML = '首页';
                $Div.appendChild($A);
            }                        
            //上一页
            if (nowNum > 1) {
                var $A = document.createElement('a');
                // $A.href = '/music/searchInfo'+'?value='+scodevalue+'&start=0';
                $A.href = '/music/searchInfo'+'?value='+scodevalue+'&start='+ (nowNum - 1)*15;
                $A.innerHTML = '上一页';
                $Div.appendChild($A);                
            }
             
            // 9个一组
            if (allNum <= 9) {
                for (var i = 1; i <= allNum; i++) {
                    var $A = document.createElement('a');
             
                    $A.href = '/music/searchInfo'+'?value='+scodevalue+'&start='+ i*15;
                    if (nowNum === i) {
                        $A.innerHTML = i;
                    }else {
                        $A.innerHTML = '[' + i + ']';    
                    }                    
                    $Div.appendChild($A);
                }
            }else {
                // 以nowNum数为中心 一共 9个数  向左右两侧扩散5 - 1 个数
                for (var i = 1; i <= 9; i++) {
                    var $A = document.createElement('a');
                    // 当前页数 小于5 时 向左扩散会出现小于1的书 要做特殊处理
                    if (nowNum < 5) {
                        $A.href = '/music/searchInfo'+'?value='+scodevalue+'&start=' + i*15;
                        if (nowNum === i) {
                            $A.innerHTML = i;
                        }else {
                            $A.innerHTML = '[' + i + ']';     
                        }                          
                    }else if (allNum - nowNum < 4) {
                        // 最后几页向右扩散 时也会出问题  所以阻止扩散 只显示最后九页
                        $A.href = '/music/searchInfo'+'?value='+scodevalue+'&start=' + (allNum - 9 + i)*15;
                        // 特殊处理后4几页
                        if ( (allNum - nowNum) === 0 && i === 9 ) {
                            $A.innerHTML = allNum - 9 + i; 
                        }else if ( (allNum - nowNum) === 1 && i === 8 ) {
                            $A.innerHTML = allNum - 9 + i; 
                        }else if ( (allNum - nowNum) === 2 && i === 7 ) {
                            $A.innerHTML = allNum - 9 + i; 
                        }
                        else if ( (allNum - nowNum) === 3 && i === 6 ) {
                            $A.innerHTML = allNum - 9 + i; 
                        }        
                                                                                            
                        else {
                            $A.innerHTML = '['+ (allNum - 9 + i) + ']';                            
                        }                                                 
                    }
                    // 正常处理方式 以nowNum为中心 向两侧扩散 4个数
                    else {
                        $A.href = '/music/searchInfo'+'?value='+scodevalue+'&start=' + (nowNum - 5 + i)*15;
                        if (i === 5) {
                            $A.innerHTML = nowNum - 5 + i;
                        }else {
                            $A.innerHTML = '[' + ( nowNum - 5 + i ) + ']';
                        }    
                    }
                    $Div.appendChild($A);                      
                }
            }    
            
            // 尾页
            if ( (allNum - nowNum) > 5) {
                var $A = document.createElement('a');
                $A.href = '/music/searchInfo'+'?value='+scodevalue+'&start=' + allNum*15;
                $A.innerHTML = '尾页';
                $Div.appendChild($A);                
            }
            // 下一页
            if ((allNum -nowNum) > 0) {
                var $A = document.createElement('a');
                $A.href = '/music/searchInfo'+'?value='+scodevalue+'&start=' + (nowNum + 1)*15;
                $A.innerHTML = '下一页';
                $Div.appendChild($A);                 
            }          
                         
            var $All = document.getElementsByTagName('a');            
            for (var i = 0; i < $All.length; i++) {
                $All[i].onclick = function () {  

                    var nowNum = parseInt(this.getAttribute('href').substring(1));
                    $Div.innerHTML = '';  
                        
                }
            }   
                                                                                                                              
        }