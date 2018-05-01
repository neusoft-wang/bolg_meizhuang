define('home', function(require, exports, module) {
  var ajax = require('util/ajax'),
    misc = require('util/misc'),
    template = require('ui/template'),
    cgi = require('module/cgi'),
    site = require('ui/page/site'),
    Cookie = require('util/cookie'),
    common = require('shop_web/common');

  // var $j_special_list,
  var $j_discovery_list;
  var $j_slider_imgs;
  var _isShowingDiscoveryDetail;
  var uuid = Cookie('uuid');

  var page = {};

  page.templates = {
    'specials': '<% for(var i=0; i<datas.length; i++){ %>\
                                        <li data-id="<%=datas[i].id%>" data-type="now" >\
                                                <div class="fls-goods">\
                                                        <% for(var j=0; j<datas[i].images_list.length; j++){ %>\
                                                                <img title="小红书_<%=datas[i].desc%>-<%=datas[i].title%>" alt="小红书_<%=datas[i].desc%>-<%=datas[i].title%>" src="<%=datas[i].images_list[j].image%>" class="<%=(j>1 ? "mTop5" : "")%> <%=(j%2 !=0 ? "mLeft5" : "")%>" />\
                                                <% } %>\
                                                </div>\
                                                <div class="fls-time">\
                                                        <div class="info">距离结束还剩</div>\
                                                        <div class="info2"><%=remainHours(datas[i].end_time.replace(/-/g,"/"))%></div>\
                                                </div>\
                                                <img class="fls-slogan" src="<%=datas[i].image%>" alt="小红书-<%=datas[i].desc%>" title="小红书_<%=datas[i].desc%>" />\
                                        </li>\
                        <% } %>',
    'notes': '<% for(var i=0; i<datas.length; i++){ %>\
                                        <li data-id="<%=datas[i].id%>" class="<%=((i+1)%4 ==0 ? "fourth" : "")%>" >\
                                                        <div class="note-info j_note_info">\
                                                                <div class="note-img j_lazyload">\
                                                                    <a href="/discovery/item/<%= datas[i].id %>" title="小红书_<%=datas[i].name%>" >\
                                                                        <img width="100%" alt="小红书_<%=datas[i].name%>" title="小红书_<%=datas[i].name%>" src="<%=datas[i].images%>">\
                                                                    </a>\
                                                                </div>\
                                                                <div class="note-desc"><%=datas[i].desc%></div>\
                                                        </div>\
                                                        <div class="note-handle">\
                                                                <a class="note-comment">\
                                                                        <img src="//s4.xiaohongshu.com/static/img/v2/web/common/comment.png" class="png_bg">\
                                                                        <span><%=datas[i].comments%></span>\
                                                                </a>\
                                                                <a class="note-follow j_follow ">\
                                                                        <img src="//s4.xiaohongshu.com/static/img/v2/web/common/unfollow.png" class="png_bg"><span><%=datas[i].likes%></span>\
                                                                </a>\
                                                                <span class="clear"></span>\
                                                        </div>\
                                                        <% if(i==(datas.length-1)) { %>\
                                                        <img class="note-icon j_remove" src="<%=getImageUrl("/static/fls/img/shop_web/home/note_icon.png") %>" />\
                                                        <div class="loadmore j_remove">点击查看更多笔记...</div>\
                                                        <% } %>\
                                                </li>\
                        <% } %>',
    'notedetail': '<div class="discovery-detail j_discover_detail" data-id="<%=datas.id%>">\
                                        <div class="d-top-info">\
                                            <div class="d-user-info">\
                                                                        <div class="d-user-img">\
                                                                                <img src="<%=datas.user.image%>" />\
                                                                                </div>\
                                                                        <div class="d-user-desc">\
                                                                                <div class="d-user-name"><%=datas.user.nickname%></div>\
                                                                                <div class="d-share-time">于<span><%=formatTime(datas.time)%></span>分享</div>\
                                                                        </div>\
                                                                </div>\
                                                                <div class="d-t-handle">\
                                                                        <a class="d-t-talk">\
                                                                                <img src="<%=getImageUrl("/static/img/v2/web/list/note/talk.png")%>">\
                                                                                <span><%=datas.comments || 0%></span>\
                                                                        </a>\
                                                                        <a class="d-t-follow j_follow discovery_like_<%=datas.id%>" d-islike="<% if (datas.inlikes) { %>1<% }else{ %>0<% } %>">\
                                                                        <% if (datas.inlikes) { %>\
                                                                                <img src="<%=getImageUrl("/static/img/v2/web/list/note/followed2.png")%>">\
                                                                        <% }else{ %>\
                                                                                <img src="<%=getImageUrl("/static/img/v2/web/list/note/unfollow2.png")%>">\
                                                                        <% } %>\
                                                                                <span><%=datas.likes%></span>\
                                                                        </a>\
                                                                </div>\
                                                                <div class="clear"></div>\
                                        </div>\
                                        <div class="d-note">\
                                                <div class="d-note-info">\
                                                                        <div class="d-note-text"><%=datas.desc%></div>\
                                                                        <div class="d-note-price">价格：<span><% if (datas.currency) { %><%=datas.currency.symbol%><% }else{ %>¥<% } %><%=datas.price%></span></div>\
                                                                        <% if (datas.location) { %><div class="d-note-add">地点：<%=datas.location%></div><% } %>\
                                                                </div>\
                                                                <div class="d-note-img">\
                                                                        <% for(var i=0,l=datas.images.length; i<l; i++){ %>\
                                                                                <img src="<%=datas.images[i].url %>" title="<%=datas.name%>_多少钱_在哪买_小红书" />\
                                                                        <% } %>\
                                                                </div>\
                                                                <div class="d-note-handle">\
                                                                        <div class="d-note-handle-btn report j_discovery_report">举报</div>\
                                                                        <div class="d-note-handle-btn share j_discovery_share"><img src="<%=getImageUrl("/static/img/v2/web/list/note/share.png")%>" ><em>分享</em></div>\
                                                                        <div class="d-note-handle-btn like j_follow discovery_like_<%=datas.id%>" d-islike="<% if (datas.inlikes) { %>1<% }else{ %>0<% } %>"><% if (datas.inlikes) { %><img src="<%=getImageUrl("/static/img/v2/web/list/note/followed.png")%>"><% }else{ %><img src="<%=getImageUrl("/static/img/v2/web/list/note/unfollow.png")%>"><%}%><span style="display:none;"><%=datas.likes%></span><em>喜欢<em></div>\
                                                                        <div class="clear"></div>\
                                                                </div>\
                                        </div>\
                                        <div class="d-note-comment j_comment_container"></div>',
    'notecomment': '<div class="d-comment-area" style="display:none;">\
                                                        <div class="d-comment-input">\
                                                                <textarea placeholder="添加一个评论..." class="j_comment_value"></textarea>\
                                                        </div>\
                                                        <div class="d-comment-submit">\
                                                                        <input type="submit" value="确定" class="d-submit-btn j_submit_comment">\
                                                        </div>\
                                                </div>\
                                                <div class="d-comment-list j_comment_list_container">\
                                                        <% for(var i=0; i<datas.length; i++){ %>\
                                                                <div class="d-comment-li j_comment_li" data-cid="<%=datas[i].id%>" data-uid="<%=datas[i].user.id%>">\
                                                                        <div class="d-c-user-image">\
                                                                                <img src="<%=datas[i].user.image%>">\
                                                                        </div>\
                                                                        <div class="d-c-comment-info">\
                                                                                <div class="d-c-user-info">\
                                                                                        <div class="d-c-user-name j_comment_user">\
                                                                                                <span class="j_comment_user_name"><%=datas[i].user.nickname%></span>\
                                                                                                <span class="c-time"><%=datas[i].time%></span>\
                                                                                        </div>\
                                                                                        <div class="d-c-user-replay j_comment_delete" style="margin-left:20px;">\
                                                                                                <a href="javascript:;">删除</a>\
                                                                                        </div>\
                                                                                        <div class="d-c-user-replay j_comment_reply">\
                                                                                                <a href="javascript:;">回复</a>\
                                                                                        </div>\
                                                                                        <div class="clear"></div>\
                                                                                </div>\
                                                                                <div class="d-c-comment-text"><%=datas[i].content%></div>\
                                                                        </div>\
                                                                        <div class="clear"></div>\
                                                                </div>\
                                                        <% } %>\
                                                </div>\
                                                <div class="d-comment-more j_comment_more" style="display:none;">\
                                                        <div class="d-more-btn">查看更多</div>\
                                                </div>'
  };

  page.api = {
    'getSpecials': '/api/v2/store/specials',
    'getNoteList': cgi.get('getNoteList', {}, false),
    'getDisCoveryDetail': '/api/discovery/item',
    'getComment': '/api/discovery/get_comment'

  };

  page.init = function() {
    // site.init({
    //   forceShow: false
    // });
    page.initVar();
    page.initPage();
    page.initEvent();
  };

  page.initVar = function() {
    // $j_special_list = $('.j_special_list');
    $j_discovery_list = $('.j_discovery_list');
    $j_slider_imgs = $('.j_slider_imgs');

    _isShowingDiscoveryDetail = false;
  };

  page.initPage = function() {
    page.loadBanners();
    page.fillData();
  };

  var mLeftGap;
  var str = {
    'close': '<img class="close j_prop_close" src="' + misc.getImageUrl("/static/fls/img/shop_web/home/prop/pop_close.jpg") + '">',
    'newcomer': '<img class="code-img" alt="小红书_6月6日_新人注册_即送66元" title="小红书_6月6日_新人注册_即送66元" src="' + misc.getImageUrl("/static/fls/img/shop_web/home/prop/pop_bonus.jpg") + '" />',
    'real': '<img class="code-img" alt="小红书_6月6日_新人注册_即送66元" title="小红书_6月6日_周年大促_正品保证" src="' + misc.getImageUrl("/static/fls/img/shop_web/home/prop/6.6_quality.png") + '" />',
    'preview': '<img class="code-img" alt="小红书_6月6日_周年大促_满399减66再返666" title="小红书_6月6日_周年大促_满399减66再返666" src="' + misc.getImageUrl("/static/fls/img/shop_web/home/prop/pop_view.jpg") + '" />',
    'video': '<div class="video-area"><div class="video-slogan">我们来了，带着现金和鲜肉</div><div class="video-source j_video_source"><img class="video-img" alt="小红书_史上颜值最高的广告" title="小红书_史上颜值最高的广告" src="' + misc.getImageUrl("/static/fls/img/shop_web/home/prop/play.png") + '" /></div></div></div>'
  };

  page.loadBanners = function() {
    $('.j_6_6').on('click', function() {
      var d_key = $(this).attr('data-key');
      if (d_key == 'download-modal') {
        $(".modal-wrapper").toggle();
      } else if (d_key == 'download') {
        window.open('/index');
      } else if (d_key == 'real') {
        location.href = '/quality';
      } else if (d_key == 'video') {
        var style = {
          'width': 0.5 * $(window).width() + 'px',
          'height': 0.6 * $(window).height() + 'px',
          'margin-left': -0.25 * $(window).width() + 'px',
          'margin-top': -0.25 * $(window).height() + 'px'
        };
        $('.j_qrcode_area').css(style).html(str[d_key] + str['close']);
        var $j_prop_close = $('.j_prop_close');
        $j_prop_close.css('left', 0.5 * $(window).width() * 0.96 + 'px');
        var $j_video_source = $('.j_video_source');
        $j_video_source.css({
          'width': 0.5 * $(window).width() - 40 + 'px',
          'height': 0.6 * $(window).height() - 40 - 48 + 'px',
          'text-align': 'center',
          'cursor': 'pointer'
        });
        $j_video_source.find('img').css("margin-top", 0.5 * (0.6 * $(window).height() - 40 - 48 - 140) + 'px');
        $('.j_prop_6_6').show();
        $('.j_gap').css("opacity", "0");
        // $j_video_source.on('click',function(){

        var img_html = $j_video_source.html();
        var videoHtml = '<embed src="http://static.youku.com/v1.0.0535/v/swf/loader.swf?VideoIDS=XOTY2MzY2Nzky&isAutoPlay=true&embedid=MjcuMTE1LjExNy4yMjICMjQxNTkxNjk4AgI%3D&wd=&vext=pid%3D%26emb%3DMjcuMTE1LjExNy4yMjICMjQxNTkxNjk4AgI%3D%26bc%3D%26type%3D0" allowFullScreen="true" quality="high" width="' + $j_video_source.width() + '" height="' + $j_video_source.height() + '" align="middle" allowScriptAccess="always" type="application/x-shockwave-flash"></embed>';

        $j_video_source.html(videoHtml);

        // setTimeout(function(){

        // 	$j_video_source.html(img_html);

        // 	$j_video_source.on('click',function(){
        // 		var img_html = $j_video_source.html();
        // 		$j_video_source.html(videoHtml);
        // 		setTimeout(function(){
        // 			$j_video_source.html(img_html);
        // 		},33*1000);
        // 	});

        // }, 100*1000);
        // });
        $j_prop_close.on('click', function() {
          $('.j_prop_6_6').attr("style", "").hide().html('');
          $('.j_gap').css("opacity", "0.7");
        });
      } else {
        $('.j_qrcode_area').html(str[d_key] + str['close']);
        $('.j_prop_6_6').show();
        $('.j_gap').css("opacity", "0");
        $('.j_prop_close').on('click', function() {
          $('.j_prop_6_6').hide().html('');
          $('.j_gap').css("opacity", "0.7");
        });
      }
    });

  };

  page.initEvent = function() {
    common.initEvent('lateScroll');
     $('body').on('click', '.note-img a', function(e) {
       e.preventDefault();
     });
     $('.j_link').on('click', function() {
       location.href = $(this).attr('data-link');
     });
    $('.j_shadow_area').on('click', function() {
      $('.j_hide').attr("style", "").hide().html('');
      $('.j_gap').css("opacity", "0.7");
      _isShowingDiscoveryDetail = false;
    });
  };

  page.lateEvent = function() {
    $('.j_special_list li').on('click', function() {
      window.snowplow('trackStructEvent:wapT', 'pc_home', 'click_view_sale', $(this).attr('data-id'));
    });

    // var $j_info2_times = $('.j_info2_time');
    // for (var i = 0; i < $j_info2_times.length; i++) {
    // 	var curDiv = $j_info2_times.eq(i);
    // 	var end_time = common.remainHours(curDiv.attr('data-endtime'));
    // 	curDiv.html(end_time);
    // };
  };

  page.lateNoteEvent = function() {
    // $j_discovery_list.delegate('.j_follow', 'click', function(){
    // 	var id=$(this).parents('.j_discovery_list').attr('d-nid')
    // 	updateFollowStatus(this, id);
    // 	return false;
    // });
    $j_discovery_list.delegate('.j_note_info', 'click', function() {
      var cur_li = $(this).parent();
      if (cur_li.hasClass('j_loadmore')) {
        return false;
      }
      if (_isShowingDiscoveryDetail) {
        return;
      };
      ajax.get({
        url: page.api.getDisCoveryDetail,
        data: {
          'oid': 'discovery.' + cur_li.attr('data-id')
        },
        success: function(resp) {
          if (resp.success) {
            _isShowingDiscoveryDetail = true;
            showDiscoveryDetail(resp.discovery);
          };
        }
      });
      return false;
    });
    var li = $j_discovery_list.find('li');
    var last_li = li.eq(li.length - 1);
    last_li.addClass('j_loadmore');
    last_li.find('.note-img').addClass('opacity20');
    last_li.on('click', function() {
      var $this = $(this);
      $this.off('click');
      $this.removeClass('j_loadmore');
      $this.find('.note-img').removeClass('opacity20');
      $this.find('.j_remove').remove();
      page.fillNotes($this.attr('data-id'));
    });
    if (li.length > 4 * 4) {
      $j_discovery_list.css("height", Math.ceil(li.length / 4) * 480 + 50 + "px");
    }
  };

  page.fillData = function() {
    // page.fillSpecials();
    //page.fillNotes('');
    page.lateNoteEvent();
  };

  // page.fillSpecials = function() {
  //   var special_li = '';
  //   ajax.get({
  //     url: page.api.getSpecials,
  //     data: {
  //       'platform': 'pc'
  //     },
  //     success: function(resp) {
  //       if (resp.result == '0') {
  //         if (resp.data.length > 0) {
  //           if (resp.data.length > 3) {
  //             resp.data = resp.data.splice(0, 3)
  //           }
  //           special_li = template.parse(page.templates.specials, {
  //             datas: resp.data,
  //             getImageUrl: misc.getImageUrl,
  //             remainHours: common.remainHours
  //           });
  //           $j_special_list.html(special_li);
  //           page.lateEvent();
  //         } else {
  //           $j_special_list.html('<li>&nbsp;</li><li>&nbsp;</li><li>&nbsp;</li><li>&nbsp;</li><li>&nbsp;</li>');
  //         }
  //       }
  //     },
  //     fail: function(resp) {}
  //   });
  // };

  page.fillNotes = function(start_id) {
    ajax.get({
      url: page.api.getNoteList,
      data: {
        num: '16',
        start: start_id,
        oid: 'stype.peak_newest'
      },
      dataType: 'json',
      success: function(resp) {
        if (resp.success && resp.array && resp.array.length > 0) {
          resp.array = resp.array.splice(0, 16);
          var note_li = template.parse(page.templates.notes, {
            datas: resp.array,
            handle: misc.convertContent,
            formatTime: misc.formatTime,
            curTime: new Date().getTime(),
            getImageUrl: misc.getImageUrl
          });
          $j_discovery_list.append(note_li);
          $j_discovery_list.parents('.mainnote').show();

          page.lateNoteEvent();
        }
      },
      fail: function() {}
    });
  };

  function showDiscoveryDetail(discovery) {
    var comments = '';
    ajax.get({
      url: page.api.getComment,
      data: {
        'discovery_id': discovery.id
      },
      success: function(resp) {
        if (resp.success) {
          comments = resp.comments;
          var notedetail = template.parse(page.templates.notedetail, {
            datas: discovery,
            formatTime: discovery_time_factory,
            getImageUrl: misc.getImageUrl
          });
          var notecomment = template.parse(page.templates.notecomment, {
            datas: comments
          });
          $('.j_prop').show();
          $('.j_prop_area').css({
            "height": 0.9 * $(window).height() + "px",
            "margin-top": -0.5 * 0.9 * $(window).height() + "px"
          }).html(notedetail);
          $('.j_comment_container').html(notecomment);
        };
      }
    });
  }

  function discovery_time_factory(time) {
    var time_final = time;
    var time_array = time.split(' ');
    if (time_array.length == 2) {
      var time_date_string = time_array[0];
      var time_time_string = time_array[1];
      var year = time_date_string.split('-')[0];
      var month = time_date_string.split('-')[1];
      var day = time_date_string.split('-')[2];
      var now = new Date();
      if (year == now.getFullYear() && month == (now.getMonth() + 1) && day == (now.getDate())) {
        time_final = time_time_string;
      } else {
        time_final = month + '-' + day;
      }
    };
    return time_final;
  }


  $(".banner").on("click",function(){
    $(".modal-wrapper").toggle();
  })
  return page;
});
