function jump(elem){
    var a = $('#input');
    a.focus();

    //提示
    var name = $(elem).parent().prev().find('a').eq(0).text() ;
    var insertHtml = '<span>回复：' + name + '</span>'
    insertHtml += '<span><a href="#" onclick="cancel()" class="c_bl">取消</a></span>';
    var con = '回复：' + name;
    if($('#notice:has(span)').length){
        $('#notice').find('span').eq(0).text(con);
    }else {
        $('#notice').append(insertHtml);
    }

    //取Id
    var cid = $(elem).find('span').eq(0).text();
    $('#commid').text(cid);
    commentId = $('#commid').text();
    console.log($('#commid').text());

    //闪烁
    var t = false,i =0;
    if(t) return;
    t = setInterval(function(){
        i++;
        if(i%2 == 0){
            a.addClass("layui-bg-red");
        }else {
            a.removeClass("layui-bg-red");
        }
        if(i== 6){
            clearInterval(t);
            a.removeClass("layui-bg-red");
        }
    }, 200);
};
function cancel(){
    $('#notice').empty();
    $('#commid').text(0);
    $('#comt').val("");
    commentId = 0;
};

function appendli(result){
    var data = result.data;
    var insertHtml
        = '<li class="li-item">'
        + '<div class="li-item-header">'
        + '<div class="li-item-left">'
        + '<a href="/user/blog?id='+ data.userId + '">'
        + '<img src="' + data.userImg + '" class="li-avatar">'
        + '</a>'
        + '</div>'
        + '<div class="li-item-center">'
        + '<h5 class="li-title">'
        + '<a>'+ data.userName +'</a>'
        + '<span class="art-meta-item">'+ data.createTime +'</span>'
        + '</h5>';
    if(data.parent != null){
        var parent = data.parent;
        insertHtml += '<div class="art-reply">'
            +'<blockquote class="layui-elem-quote layui-quote-nm art-yin">'
            + '<div>'
            + '<img src="' + parent.userImg + '" class="art-yin-img">'
            + '<span>'+ parent.userName +'</span>'
            + '<span class="art-meta-item">'+ parent.createTime +'</span>'
            + '</div>'
            + '<div>'
            + '<p>'+ parent.content +'</p>'
            + '</div>'
            + '</blockquote>'
            + '</div>';
    }
    insertHtml += '<div class="ping-p">'
        + '<p>'+ data.content +'</p>'
        + '</div>'
        + '</div>'
        + '<div class="li-item-right">'
        + '<a href="#" onclick="jump(this)">'
        + '<span style="display:none">' + data.commentId + '</span>'
        + '<span class="meta-item-type">回复</span>'
        + '</a>'
        + '</div></div></li>';

    $('#comul').prepend(insertHtml);
};