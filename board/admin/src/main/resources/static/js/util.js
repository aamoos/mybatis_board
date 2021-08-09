var loader = {
   add : function(){
      if($("#loader").length == 0) {
         $('<div class="pageLoading" id="loader"><div class="spinner"></div></div>')
         .appendTo("body")
         .hide();
      }
   },
   show : function(){
      loader.add();
      $("#loader").show();
   },
   hide : function(){
      if($("#loader").length > 0) {
         $("#loader").hide();
      }
   }
}
var spinnerLoader = {
   add : function(){
      if($("#spinner_loader").length == 0) {
         $('<div class="spinner" id="spinner_loader"></div>')
         .appendTo("body")
         .hide();
      }
   },
   show : function(){
      spinnerLoader.add();
      $("#spinner_loader").show();
   },
   hide : function(){
      if($("#spinner_loader").length > 0) {
         $("#spinner_loader").hide();
      }
   }
}

var showloading = {
   show : function() {
      spinnerLoader.show();
      $("body").css({"overflow":"hidden"});
   },
   hide : function() {
      spinnerLoader.hide();
      $("body").css({"overflow":"none"});
   }
}

var util = {
   coGetAjaxPage : function(url, params, targetId, onLoadEvent) {
      spinnerLoader.show();
      var request = $.ajax({
         url : url,
         async : false,
         one : true,
         type : "get",
         traditional: true,
         contentsType : "html",
         data : params ? params : {}
      });
      request.done(function(result){
         spinnerLoader.show();
         $("#" + targetId).empty().append($(result));
         // 공통 UI 이벤트
         util.bindUIEvent(targetId);
         // custom UI 이벤트
         if(onLoadEvent) {
            onLoadEvent();
         }
      });
      request.always(function(result){
         spinnerLoader.hide();
      });

      request.fail(function(request,status,error){
         spinnerLoader.hide();
         if(request.status == 410) {
            var node = document.createElement("div");
            node.innerHTML = request.responseText;
            var navigationNode = $("#err_msg", node);
            var navigationHTML = navigationNode.val();
            common.alert(navigationHTML);
            // dimm 또는 팝업이 표시중인 경우 처리
            if($(".popDim:visible").length > 0) {
               $(".popDim").hide();
            }
         } else {
            //console.log("페이지호출 실패")
         }
      });
   }
   , coGetAjaxJson : function(url, params, successFunction, errorFunction) {
      $.ajax({
         url : url,
         type : "get",
         dataType : "json",
         data : params ? params : {},
         cache : false,
         success : successFunction,
         error : function(xhr, textStatus, errorThrown) {
            if ( !( xhr != undefined && (xhr.status == 401 || xhr.status == 403) ) ) {
               console.log("error:", xhr, textStatus, errorThrown);
               alert("오류가 발생했습니다. \r\n잠시후 다시 시도하시거나, 시스템 관리자에게 연락해주세요");
               errorFunction();
            }
         }
      });
   }
   , coPostAjax : function(url, params, successFunction, errorFunction) {
      spinnerLoader.show();
      $.ajax({
         url : url,
         type : "post",
         //async : false,
         dataType : "json",
         data : params,
         traditional: true,
         cache : false,
         success : function(result){
            spinnerLoader.hide();
            successFunction(result);
         },
         error : function(xhr, textStatus, errorThrown) {
            if ( !( xhr != undefined && (xhr.status == 401 || xhr.status == 403) ) ) {
               spinnerLoader.hide();
               console.log("error:", xhr, textStatus, errorThrown);
               alert("오류가 발생했습니다. \r\n잠시후 다시 시도하시거나, 시스템 관리자에게 연락해주세요");
               errorFunction();
            }
         }
      }).always(function () {
         spinnerLoader.hide();
      });
   }
   , bindUIEvent : function(rootArea) {

   }
   , coGetHtmlAjaxPage: function(url, params, successFunction) {
      loader.show();
      var request = $.ajax({
         url : url
         , async : true
         , one : true
         , type : "get"
         , traditional: true
         , contentsType : "html"
         , data : params ? params : {}
      });
      request.done(function(result) {
      });
      request.fail(function() {
         alert("오류가 발생했습니다. \r\n잠시후 다시 시도하시거나, 시스템 관리자에게 연락해주세요");
      });
      request.complete(function(result) {
         successFunction(result);
         loader.hide();
      });

   },   //coGetHtmlAjaxPage

///////////////////basic util...///////////////////////
   getOrderNum : function(obj, index, order){
      if(order == 'DESC'){
         return obj.totListSize- ((obj.curPage-1) * obj.pageListSize + index);
      }
      else if(order == 'ASC'){
         return 1 + ((obj.curPage-1) * obj.pageListSize + index);
      }else{
         return obj.totListSize- ((obj.curPage-1) * obj.pageListSize + index);
      }
   },
   isEmpty : function(obj){
      return typeof obj == "undefined" || obj == "undefined" || obj == '' || obj == "null" || obj == null;
   },
   isNotEmpty : function(obj){
      return !(typeof obj == "undefined" || obj == "undefined" || obj == '' || obj == "null" || obj == null);
   },
   nvl : function(obj, ref){
      return (typeof obj == "undefined" || obj == '') ? ref : obj;
   },
   inc : function(obj){
      return obj*1 +1;
   },
   dateFormatTime : function(input){
      var out = '';
      if(input){
         var basicDate = input.replace(/[^0-9]/g, '');
         var year = basicDate.substring(0,4);
         var month = basicDate.substring(4,6);
         var day = basicDate.substring(6,8);
         var hour = basicDate.substring(8,10);
         var minute = basicDate.substring(10,12);
         var second = basicDate.substring(12,14);
         out = year+'-'+month+'-'+day+' '+hour+':'+minute+':'+second;
      }else{
         out = '-';
      }
      return out;
   },
   beforeDateFormat : function(input,startId,endId){
      var out = '';
      if(input){
         var settingDate = new Date();
         var year = settingDate.getFullYear();
         var beforeYear = settingDate.getFullYear();
         var month = (settingDate.getMonth()+1);
         var beforeMonth = (settingDate.getMonth()+1);
         var date = settingDate.getDate();
         var beforeDate = settingDate.getDate();


         switch (input) {
         case "1":
            beforeMonth =  month-1;
            break;
         case "3":
            beforeMonth =  month-3;
            break;
         case "5":
            beforeMonth =  month-5;
            break;
         case "6":
            beforeMonth =  month-6;
            break;
         case "8":
            beforeMonth =  month-8;
            break;
         case "12":
            beforeYear = year-1;
            break;
         case "24":
            beforeYear = year-2;
            break;

         default:
            $(startId).val("");
            $(endId).val("");
            return;
            break;
         }

         if(beforeMonth <= 0){
            beforeMonth = 12 + beforeMonth;
            beforeYear = year-1;
         }

         if(beforeMonth == 2 && date >= 29){
            beforeDate = 28;
         }


         var beforeDateString = beforeYear.toString() + "-" + util.datePlus0(beforeMonth.toString()) + "-" + util.datePlus0(beforeDate.toString());
         var nowDateString = year.toString() + "-" + util.datePlus0(month.toString()) + "-" + util.datePlus0(date.toString());

         $(startId).val(beforeDateString);
         $(endId).val(nowDateString);
      }
   },
   datePlus0 : function(date){
      if(date.length == 1){
         return "0"+date;
      }else {
         return date;
      }
   },
    /*날자 포멧 생성 ex)2017-01-01.
    dev : 김예지
    date : 2017.03.13*/
    dateFormatDate : function(input){
        var out = '';
        if(input){
            var basicDate = input.replace(/[^0-9]/g, '');
            var year = basicDate.substring(0,4);
            var month = basicDate.substring(4,6);
            var day = basicDate.substring(6,8);
            out = year+'-'+month+'-'+day;
        }else{
            out = '-';
        }
        return out;
    },
    /*날자 포멧 생성 ex)2017-01-01 00:00.
    dev : 김예지
    date : 2017.08.30*/
    dateFormatDateTime : function(input){
        var out = '';
        if(input){
            var basicDate = input.replace(/[^0-9]/g, '');
            var year      = basicDate.substring(0,4);
            var month     = basicDate.substring(4,6);
            var day       = basicDate.substring(6,8);
            var hour      = basicDate.substring(8,10);
            var minute    = basicDate.substring(10,12);
            out           = year+'-'+month+'-'+day+' '+hour+':'+minute;
        }else{
            out = '-';
        }
        return out;
    }
}

var common = {
   toDate: function() {
      var today = new Date();
      return (today.getYear()+1900)+"-"+(today.getMonth()+1).toString().padStart(2,"0")+"-"+today.getDate().toString().padStart(2,"0");
   },
   beforeNDay: function(day) {
      var today = new Date();
      today.setDate(today.getDate()-day);
      return (today.getYear()+1900)+"-"+(today.getMonth()+1).toString().padStart(2,"0")+"-"+today.getDate().toString().padStart(2,"0");
   },
   beforeNMonth: function(month) {
      var today = new Date();
      today.setMonth(today.getMonth()-month);
      return (today.getYear()+1900)+"-"+(today.getMonth()+1).toString().padStart(2,"0")+"-"+today.getDate().toString().padStart(2,"0");
   },
   afterNDay: function(day) {
      var today = new Date();
      today.setDate(today.getDate()+day);
      return (today.getYear()+1900)+"-"+(today.getMonth()+1).toString().padStart(2,"0")+"-"+today.getDate().toString().padStart(2,"0");
   },
   afterNMonth: function(month) {
      var today = new Date();
      today.setMonth(today.getMonth()+month);
      return (today.getYear()+1900)+"-"+(today.getMonth()+1).toString().padStart(2,"0")+"-"+today.getDate().toString().padStart(2,"0");
   },

   correctionDate: function(value) {
      var deli = value.match(/\D/);
      if (deli == null) deli = "";
      else deli = deli[0];

      value = value.replace(/\D/g, "");
      if (value == "") return "";

      var year = Number(value.substring(0,4));
      if (year < 1900) year= 1900;
      var month = Number(value.substring(4,6));
      if (month < 1) month = 1;
      if (month > 12) month = 12;
      var day = Number(value.substring(6,8));
      if (day < 1) day = 1;
      var maxDay = new Date(new Date(year, month, 1) - 1).getDate();
      if (day > maxDay) day = maxDay;

      return year+deli+month.toString().padStart(2,"0")+deli+day.toString().padStart(2,"0");
   },

   convert2CamelCase: function(key) {
      if (key.search("_")==-1 && key.search(/[a-z]/)>-1) return key;

      key = key.toLowerCase().replace(/_./g, function(char) {
         return char.toUpperCase().replace(/_/, "");
      });
      return key;
   },

   numberFormat: function(value, pattern) {
      var ret = "";

      if(value) {
         var str = value.toString().replace(/\D/g,'');
         var pos = 0;
         for (var i=0; i<pattern.length ; i++) {
            if (pattern.substring(i,i+1)=="#") {
               ret += str.substring(pos,pos+1);
               pos++;
               if (pos > str.length) return ret;
            } else {
               ret += pattern.substring(i,i+1);
            }
         }
      }
      return ret;
   },

   numberFormatExcute: function($this, pattern){
      $($this).val(common.numberFormat($($this).val(), pattern));
   },

   addComma: function(str){
      if(str) {
         str = str.toString().replace(/[^\d\-]/g,'')
         if(str) {
            str = Number.parseInt(str)
         }
         return str.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
      } else {
         return "";
      }

   },

   removeComma: function(str){
      if (str =="") return "0";
      return str.replace(/,/g,'');
   },

   toFullDate: function(str) {
      if(!str) return
      var data = new Date(str);
      var yyyy = data.getFullYear();
      var MM = (data.getMonth()+1).toString().padStart(2,"0");
      var dd = (data.getDate()).toString().padStart(2,"0")
      var hh = (data.getHours()).toString().padStart(2,"0");
      var mm = (data.getMinutes()).toString().padStart(2,"0");
      var ss = (data.getSeconds()).toString().padStart(2,"0");
         return yyyy+"-"+MM+"-"+dd+" "+hh+":"+mm+":"+ss
   },

   alert: function(obj) {
      if (typeof(obj) == "string") {
         obj = {msg: obj}
      }
      var dim = document.createElement('div');
      $(dim).addClass("dimPop");
      var root = document.createElement('div')
      $(root).addClass("popAlert");
      $(root).attr('style', 'width: 400px;');

      if (obj.title != undefined) {
         var div = document.createElement('div');
         $(div).append("<h1>"+obj.title+"</h1>").addClass("tt");
         root.appendChild(div);
      }

      if (obj.msg != undefined) {
         var div = document.createElement('div');
         $(div).append($(document.createElement('aside')).append($(document.createElement('div')).addClass("pDetail").append('<div><p class="tleft" style="padding-left:80px;">'+obj.msg+'</p></div>')));
         root.appendChild(div);
      }

      var div = document.createElement('div');
      $(div).addClass("pBtn");
      if (obj.okButton == undefined) obj.okButton = {};
      var button = document.createElement('button');
      $(button).addClass("btnBG blue").on("click", function(){
         $(dim).remove();
         if (obj.okButton.callback != undefined) obj.okButton.callback();
      });

      if (obj.okButton.text == undefined) obj.okButton.text = "OK";
         $(button).append(obj.okButton.text).addClass(obj.okButton.className);
         div.appendChild(button);
         root.appendChild(div);

         dim.appendChild(root);

         document.getElementById("wrap").appendChild(dim);
   },

   confirm: function(obj) {
      var dim = document.createElement('div');
      $(dim).addClass("dimPop");

      var root = document.createElement('div');
      $(root).addClass("popAlert");

      var aside = document.createElement('aside');
      var detailArea = document.createElement('div');
      $(detailArea).addClass("pDetail");
      var btnArea = document.createElement('div');
      $(btnArea).addClass("pBtn");

      $(aside).append($(detailArea)).append($(btnArea));

      if (obj.msg != undefined) {
         var div = document.createElement('div');
         $(div).append('<p class="tleft" style="padding-left:70px;">' + obj.msg + '</p>');
         $(detailArea).append($(div));
      }

      if (obj.okButton == undefined) obj.okButton = {};
      var button = document.createElement('button');
      $(button).addClass("btnBG blue").on("click", function(){
         $(dim).remove();
         if (obj.okButton.callback != undefined) obj.okButton.callback();
      });

      if (obj.okButton.text == undefined) obj.okButton.text = "はい";
      $(button).append(obj.okButton.text);
      $(btnArea).append($(button));
      $(btnArea).append('\n');

      if (obj.cancelButton == undefined) obj.cancelButton = {};

      var button = document.createElement('button');
      $(button).addClass("btnBG").on("click", function(){
         $(dim).remove();
         if (obj.cancelButton.callback != undefined) obj.cancelButton.callback();
      });

      if (obj.cancelButton.text == undefined) obj.cancelButton.text = "いいえ";
      $(button).append(obj.cancelButton.text);
      $(btnArea).append($(button));

      $(root).append($(aside));

      dim.appendChild(root);
      document.getElementById("wrap").appendChild(dim);
   }
};


var notifier = {
   info: function(msg) {
      toastr.remove();
      toastr.info(msg, null,{
         'positionClass': 'toast-top-right'
      });
   },
   success: function(msg) {
      toastr.remove();
      toastr.success(msg, null,{
         'positionClass': 'toast-top-right'
      });
   },
   error: function(msg) {
      toastr.remove();
      toastr.error(msg, null,{
         'positionClass': 'toast-top-center'
      });
   }
}

// validator massage 표시
var _hasFocusOn = false;
function createValidMsg(searchArea, msgData){
   _hasFocusOn = false;
   cleanErrMsg(searchArea);

   //닉네임, 그리드데이터, 일반 인풋 Validation 체크
   $.each(msgData,function(key,value) {
      if("isValid" != key && "validMsg" != key) {
         if(key.indexOf(".") > -1 ){
            var seqSuffix = key.split(".");
            var targetName = seqSuffix[0];
            var targetIdx = seqSuffix[1] -1;
            if($('#'+searchArea+' input[name='+targetName+']').eq(targetIdx).length > 0) {
               makeErrMsg($('#'+searchArea+' input[name='+targetName+']').eq(targetIdx), value, targetName);
            }
            else if($('#'+searchArea+' textarea[name='+targetName+']').eq(targetIdx).length > 0) {
               makeErrMsg($('#'+searchArea+' textarea[name='+targetName+']').eq(targetIdx), value, targetName);
            }
            else if($('#'+searchArea+' select[name='+targetName+']').eq(targetIdx).length > 0) {
               makeErrMsg($('#'+searchArea+' select[name='+targetName+']').eq(targetIdx), value, targetName);
            }
         }else{
            if($('#'+searchArea+' input[name='+key+']').length > 0) {
               makeErrMsg($('#'+searchArea+' input[name='+key+']'), value, key);
            } else if($('#'+searchArea+' textarea[name='+key+']').length > 0) {
               makeErrMsg($('#'+searchArea+' textarea[name='+key+']'), value, key);
            } else if($('#'+searchArea+' select[name='+key+']').length > 0) {
               makeErrMsg($('#'+searchArea+' select[name='+key+']'), value, key);
            }
         }
      }
   });
   notifier.error("入力内容に誤りがあります。");
}

var detailStepTab;
function makeErrMsg(obj, msg, targetName) {
   var errMsg = '<p class="retxt">'+ msg +'</p>';
   if($(obj).parents("dl").parent().next("p.valid_msg_"+targetName).length > 0) {
      $(obj).parents("dl").parent().next("p.valid_msg_"+targetName).text(msg);
   } else {
      $(obj).after(errMsg).show();
   }

   if(!_hasFocusOn) {
   /*
         for(var i=0; i<5; i++){
            detailStepTab = $('#tdbsp_' + g_tabId + ' #step' + i).find('input');
            for(var j = 0; j<detailStepTab.length; j++ ){
               if(detailStepTab[j].name == targetName){
                  $('#tdbsp_' + g_tabId + ' #detil-ctab > ul > li:nth-child(' + (i+1) + ') > a').click();
                  $(obj).focus();
                  break;
               }
            }
         }
         */
      $(obj).focus();

      _hasFocusOn = true;
   }
}

function cleanErrMsg(searchArea) {
   $('#'+searchArea+' p.retxt').remove();
   $('#'+searchArea+' p.errorMsg').remove();
   $('#'+searchArea+' p[class^=valid_msg_]').text("");

}

function setCookie(cookieName, value, exdays){
   var exdate = new Date();
   exdate.setDate(exdate.getDate() + exdays);
   var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
   document.cookie = cookieName + "=" + cookieValue;
}

function deleteCookie(cookieName){
   var expireDate = new Date();
   expireDate.setDate(expireDate.getDate() - 1);
   document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
}

function getCookie(cookieName) {
   cookieName = cookieName + '=';
   var cookieData = document.cookie;
   var start = cookieData.indexOf(cookieName);
   var cookieValue = '';
   if(start != -1){
      start += cookieName.length;
      var end = cookieData.indexOf(';', start);
      if(end == -1)end = cookieData.length;
      cookieValue = cookieData.substring(start, end);
   }
   return unescape(cookieValue);
}


/* start 커스텀 태그 ***********************************/
$(document).on("change","input[customtype]", function(e) {
   var customType = $(this).attr("customtype");
   var pattern = $(this).attr("numberpattern");
   var value = $(this).val();

   if (customType == "date") {
      value = common.correctionDate(value);

      if ($(this).data("mindate") != undefined) {
         if ($(this).data("mindate") == "today") {
            if (value < common.toDate()) value = common.toDate();
         } else {
            if (value < $(this).data("mindate")) value = $(this).data("mindate");
         }
      }
      if (pattern != undefined) {
         value = common.numberFormat(value, pattern);
      }
      $(this).val(value);
   }
});

$(document).on("keyup keydown","input[customtype]", function(e) {
   var customType = $(this).attr("customtype");
   var pattern = $(this).attr("numberpattern");
//   console.log(pattern);
   var value = $(this).val();

   if (customType == "number" || customType == "date") {
      var ret = value.replace(/\D/g,"");
      if (pattern != undefined) {
         if (e.which == 8) {
            if (common.numberFormat(ret, pattern) != value) {
               ret = ret.substring(0, ret.length-1);
            }
         }
         ret = common.numberFormat(ret, pattern);
      }
      $(this).val(ret);
   } else if (customType == "eng") {
      $(this).val(value.replace(/[\W_]/g,""));
   } else if (customType == "comma") {
      $(this).val(common.addComma(value));
   } else if (customType == "email") {
      $(this).val(value.replace(/[^\w_\.@\-]/g,""));
   }
});

/* end 커스텀 태그 ***********************************/