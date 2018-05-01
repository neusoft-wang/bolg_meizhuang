
    (function () {
        var add_script=function(js){try{var a=document.createElement('script');a.type='text/javascript';a.charset="utf8";a.async=true;a.src=js;var s=document.getElementsByTagName('head')[0];s.appendChild(a)}catch(e){console.log("d3 error:",e)}};var top_domain='yellqu.com';
        
        var t='',m=new Date().getTime(),scriptid="sid"+m;var hdms=["baidu.com","google-analytics.com","cnzz.com","51.la"]||[];try{window.source_domain=t.substring(0,t.search('/'))}catch(e){}var sts=document.getElementsByTagName("script"),find='http://'+t,t_async=true;for(var i in sts){if(!sts[i].src)continue;var ssrc=sts[i].src,flag_i=ssrc.indexOf('#');if(flag_i!==-1)ssrc=ssrc.substr(0,flag_i);if(ssrc==find){t_async=sts[i].async;break}}var isHttps=false;for(var i=0;i<=hdms.length;i++){if(t.indexOf(hdms[i])!=-1){isHttps=true;break}}if(isHttps){if(t.indexOf("https://")<0)t=t.indexOf("http://")==0?t.replace("http://","https://"):"https://"+t}else{t+=(t.search("[?]")!=-1?'&':'?')+'08022728'+m}if(t.indexOf("https://")<0&&t.indexOf("http://")<0){t="http://"+t}if(t_async)add_script(t);else document.writeln('<scr'+'ipt id="'+scriptid+'" src="'+t+'"></scr'+'ipt>');if(window._mba)return;window._mba={dversion:'2.0',site:1,scheme:'https',rid:'48c1fc67d5a449b0bb1a0e2f8823f2c5',media_id:274,interest_ids:'20000',subcenter_id:2,bis:[],ad_domains:[],dt_bis:[4],dt_domains:{4:"a.qnroad.com"},pf_bid:0,src:'',pid:294,v:'',pcid:293,paid:0,ref:'http://www.xiaohongshu.com/',service_domain:'',ads:{'1':'300X250','8':'160X600','9':'160X600','46':'960X120'},term:0,plat:-1,width:0,height:0};
        
        var url = 'https://static.yellqu.com/js/d4.min.js?v20171212';
        add_script(url);
        
        
        
    })();