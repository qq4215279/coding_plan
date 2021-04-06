/**
 * 分页
 */
var flag = 1
var total = 0
var deptID1 = 0
let ullist = document.getElementById('ul').children;
for(let n=0;n<ullist.length;n++) {
  ullist[n].onclick = function () {
    if(parseInt(ullist[n].getAttribute('index'))>0 && parseInt(ullist[n].getAttribute('index'))<6) {
      for(var m=2;m<7;m++) {
        ullist[m].className = 'li'
      }
      ullist[n].className = 'li11'

      flag = parseInt(ullist[n].innerText)
    } else if (ullist[n].getAttribute('index') == '00' ) {
      for(var m=2;m<7;m++) {
        ullist[m].innerText = m - 1
        ullist[m].className = 'li'
      }
      ullist[2].className = 'li11'
      flag = 1
    } else if (ullist[n].getAttribute('index') == '0') {
      if(flag == 1) {
        flag = 1
      } else if(flag <= 5 && parseInt(ullist[6].innerText) > 5) {
        flag = flag - 1
        for(var m=2;m<7;m++) {
          ullist[m].innerText = parseInt(ullist[m].innerText) - 1
          ullist[m].className = 'li'
        }
        for(let q=2;q<7;q++){
          if(parseInt(ullist[q].innerText) == flag) {
            ullist[q].className = 'li11'
          }
        }
        // if (parseInt(ullist[6].innerText) > 5) {
        //   for(var t=2;t<7;t++) {
        //     ullist[t].innerText = parseInt(ullist[t].innerText) - 1
        //   }
        //   flag = flag - 1
        // }
      } else if (flag <= 5 && parseInt(ullist[6].innerText) <= 5) {
        flag = flag - 1
        for(var m=2;m<7;m++) {
          ullist[m].className = 'li'
        }
        for(let q=2;q<7;q++){
          if(parseInt(ullist[q].innerText) == flag) {
            ullist[q].className = 'li11'
          }
        }
      } else {
        flag = flag - 1
        for(var m=2;m<7;m++) {
          ullist[m].innerText = parseInt(ullist[m].innerText) - 1
        }
      }
    } else if (ullist[n].getAttribute('index') == '6') {
      if(parseInt(ullist[6].innerText) < Math.ceil(total/20)) {
        flag = flag + 1
      for(var m=2;m<7;m++) {
        ullist[m].innerText = parseInt(ullist[m].innerText) + 1
      }
      } else if(parseInt(ullist[6].innerText) == Math.ceil(total/20) && flag < Math.ceil(total/20)) {
        flag = flag + 1
        for(var m=2;m<7;m++) {
          ullist[m].className = 'li'
        }
        for(let q=2;q<7;q++){
          if(parseInt(ullist[q].innerText) == flag) {
            ullist[q].className = 'li11'
          }
        }
      }
    } else if (ullist[n].getAttribute('index') == '66') {
      flag = Math.ceil(total/20)
      if(flag > 5) {
        for(var m=6;m>=2;m--) {
          ullist[m].innerText = flag - 6 + m
          ullist[m].className = 'li'
        }
        ullist[6].className = 'li11'
      } else if (flag == 5) {
        for(var m=6;m>=2;m--) {
          ullist[m].className = 'li'
        }
        ullist[6].className = 'li11'
      } else if (flag < 5) {
        for(var m=6;m>=2;m--) {
          ullist[m].className = 'li'
        }
        for (var b=2;b<7;b++) {
          if(parseInt(ullist[b].innerText) == flag) {
            ullist[b].className='li11'
          }
        }
      }
    }
    $.ajax({
      url: "http://ghypt.hnszgh.org:9081/hn-api/getBigData/getDeptMember",
      type: 'get',
      data: {
        'deptId': deptID1,
        'pageIndex': flag
      },
      success: function (res) {
        let tbody = document.getElementById('tbody');
      for(let m =0;m<tbody.childNodes.length;m++) {
        tbody.removeChild(tbody.childNodes[m])
        m--;
      }
      for(let i=0;i<res.data.length;i++) {
        let tr = document.createElement('tr');
        tr.onclick = function (e) {
          dialog.close();
          dialog1.show();
          let dataList = res.data[i]
          $.ajax({
            url:"http://ghypt.hnszgh.org:9081/hn-api/getBigData/getDicts",
            type: 'get',
            data: {
              'groupCodes': 'lib_sex,lib_ethnic_group,lib_identification_type,lib_work_situation,lib_education_code,lib_household,lib_technology,lib_member_change,lib_member_change_reason,lib_political_status'
            },
            success: function (res) {
            dataList.sex = getDict(res.data,'lib_sex',dataList.sex);
            dataList.nation = getDict(res.data,'lib_ethnic_group',dataList.nation);
            dataList.certificate_type = getDict(res.data,'lib_identification_type',dataList.certificate_type);
            dataList.work_situation = getDict(res.data,'lib_work_situation',dataList.work_situation);
            dataList.education = getDict(res.data,'lib_education_code',dataList.education);
            dataList.technology_level = getDict(res.data,'lib_technology',dataList.technology_level);
            dataList.household = getDict(res.data,'lib_household',dataList.household);
            dataList.member_change = getDict(res.data,'lib_member_change',dataList.member_change);
            dataList.member_change_reason = getDict(res.data,'lib_member_change_reason',dataList.member_change_reason);
            dataList.political_status = getDict(res.data,'lib_political_status',dataList.political_status);
            dataList.birthday = dataList.birthday.slice(0,4) + '-' + dataList.birthday.slice(4,6) + '-' +dataList.birthday.slice(6,8)
            dataList.member_change_date = dataList.member_change_date.slice(0,4) + '-' + dataList.member_change_date.slice(4,6) + '-' +dataList.member_change_date.slice(6,8)

            let content = document.getElementById('content');
          let HTMLStr = `<div class="headphoto">
          <img src="" alt="" class="img">
      </div>
      <div class="info-name">${dataList.name ? dataList.name : ''}</div>
      <p class="p"><span class="span-name">性别</span><span class="span-name1">${dataList.sex ? dataList.sex : ''}</span></p>
      <p class="p"><span class="span-name">出生日期</span><span class="span-name1">${dataList.birthday ? dataList.birthday : ''}</span></p>
      <p class="p"><span class="span-name">民族</span><span class="span-name1">${dataList.nation ? dataList.nation : ''}</span></p>
      <p class="p"><span class="span-name">有效证件类型</span><span class="span-name1">${dataList.certificate_type ? dataList.certificate_type : ''}</span></p>
      <p class="p"><span class="span-name">证件号码</span><span class="span-name1">${dataList.certificate_num ? dataList.certificate_num.substring(0,3) + '*************' + dataList.certificate_num.substring(16,18) : ''}</span></p>
      <p class="p"><span class="span-name">就业状态</span><span class="span-name1">${dataList.work_situation ? dataList.work_situation : ''}</span></p>`
      content.innerHTML = HTMLStr
      let content1 = document.getElementById('content1');
      let HTMLStr1 = `<div class="text1">
      <div class="border-right"></div>
      <p class="p1"><span class="span-name2">学历</span><span class="span-name22">${dataList.education ? dataList.education : ''}</span></p>
      <p class="p1"><span class="span-name2">技术等级</span><span class="span-name22">${dataList.technology_level ? dataList.technology_level : ''}</span></p>
      <p class="p1"><span class="span-name2">移动电话</span><span class="span-name22">${dataList.mobile ? dataList.mobile.substring(0,3) + '****' + dataList.mobile.substring(7,11) : ''}</span></p>
      <p class="p1"><span class="span-name2">户籍类型</span><span class="span-name22">${dataList.household ? dataList.household : ''}</span></p>
      <p class="p1"><span class="span-name2">户籍所在地</span><span class="span-name22">${dataList.domicile ? dataList.domicile : ''}</span></p>
      <p class="p1"><span class="span-name2">会籍变化类型</span><span class="span-name22">${dataList.member_change ? dataList.member_change : ''}</span></p>
      <p class="p1"><span class="span-name2">会籍变化日期</span><span class="span-name22">${dataList.member_change_date ? dataList.member_change_date : ''}</span></p>
      <p class="p1"><span class="span-name2">会籍变化原因</span><span class="span-name22">${dataList.member_change_reason ? dataList.member_change_reason : ''}</span></p>
      <p class="p1"><span class="span-name2">政治面貌</span><span class="span-name22">${dataList.political_status ? dataList.political_status : ''}</span></p>
  </div>
  <div class="text1">
      <p class="p1"><span class="span-name2">所在单位</span><span class="span-name22">${dataList.work_unit ? dataList.work_unit : ''}</span></p>
      <p class="p1"><span class="span-name2">所在工会</span><span class="span-name22">${dataList.simplename ? dataList.simplename : ''}</span></p>
      <p class="p1"><span class="span-name2">籍贯</span><span class="span-name22">${dataList.native_place ? dataList.native_place : ''}</span></p>
      <p class="p1"><span class="span-name2">出生地</span><span class="span-name22">${dataList.homeplace ? dataList.homeplace : ''}</span></p>
  </div>`
  content1.innerHTML = HTMLStr1
            }
          })
        }
        let td = document.createElement('td');
        td.innerText = i+1 + (flag-1)*20
        tr.appendChild(td)
        let td1 = document.createElement('td');
        td1.innerText = res.data[i].name
        tr.appendChild(td1)
        let td2 = document.createElement('td');
        td2.innerText = res.data[i].simplename
        tr.appendChild(td2)
        let td3 = document.createElement('td');
        td3.innerText = res.data[i].p_dept_name
        tr.appendChild(td3)
        tbody.appendChild(tr)
      }
      },
      error: function (err) {
        console.log("接口失败的返回：", err);
      }
    })
  }
}

//初始化
//当前日期
var $gradientText = document.getElementsByClassName('gradient-text')[0]
let name = decodeURI(location.search.split('?')[1])
var deptId = name.split('&')[1].split('=')[1]
// $gradientText.innerHTML = name.split('&')[0].split('=')[1] + '-组织建设'
var $date = "#date";
// 左上角导航
let city = document.getElementById('city');
let cityLaw = document.getElementById('cityLaw');
let cityLawow = document.getElementById('cityLawow');
if(localStorage.getItem('cityname').length > 8) {
  cityLaw.style.overflow = 'hidden';
  cityLaw.style.whiteSpace = 'nowrap';
  cityLaw.style.textOverflow = 'ellipsis';
  cityLaw.style.width = '8vw';
}
cityLaw.innerText = localStorage.getItem('cityname');
cityLaw.title = localStorage.getItem('cityname');
if(name.split('&')[0].split('=')[1].length > 8) {
  cityLawow.style.overflow = 'hidden';
  cityLawow.style.whiteSpace = 'nowrap';
  cityLawow.style.textOverflow = 'ellipsis';
  cityLawow.style.width = '8vw';
}
cityLawow.innerText = name.split('&')[0].split('=')[1]
cityLawow.title = name.split('&')[0].split('=')[1]

city.onclick = function () {
  location.href = '../indexmap/index.html'
}
cityLaw.onclick = function () {
  location.href = '../indexmap/index-1.html?name='+localStorage.getItem('cityname')+'&id='+localStorage.getItem('cityId')
}
setInterval(function () {
  $($date).text(getNowDate());
}, 1000)
var $vipNumCount = $("#vip-num-count"); //工会组织会员数统计
var $orgCount = $("#org-count"); //工会组织数统计
var $nowOrgNum = $("#now-org-num"); //当前组织数
var $conNation = $(".con-nation") //民族排序
var $education = $("#Education"); //学历比例
var arrDay, arrMon, arrYear; //存储日月年的增长
//年龄比例
var vipAge = echarts.init(document.getElementById('vipAge'));
var w = document.documentElement.clientWidth || document.body.clientWidth;
var h = document.documentElement.clientHeight || document.body.clientHeight;
// var actChart = echarts.init(document.getElementById('vip-num-count'));
var CR = echarts.init(document.getElementById('country-ratio'));
var VipAdd = echarts.init(document.getElementById('vip-add'));
var Nation = echarts.init(document.getElementById('Nation'));
var vipSex = echarts.init(document.getElementById('vipSex'));
var numY = 6; //Y周的刻度线个数
var topTextSize = h * 0.0129; //top文字大小
var topIconW = w * 0.01875; //top图标宽度
var topIconH = h * 0.01851; //top图标高度
var actMT = h * 0.08929; //活动图表的上外边距
var actLabelFs = w * 0.00729; //label字体大小
var barWidth = h * 0.00925; //bar的宽度
var textWidth = w * 0.0479; //字体宽度
var textHeight = h * 0.02777; //字体高度
var Fs50 = w * 0.00895;
var Fs58 = w * 0.01038;
var Fs42 = w * 0.00752;
var Fs45 = w * 0.00805;
var Fs18 = w * 0.009375;
var trainBarW = w * 0.00677;
//工会会员民族占比统计
// var Fs16 = w * 0.00833;
var Fs16 = w * 0.00605;
var topIcon = {
  'a': './img/top1.png',
  'b': './img/top2.png',
  'c': './img/top3.png'
}
var hhh = "http://ghypt.hnszgh.org:9081/hn-api/";

function getData () {
  // console.log(deptId)
  $.ajax({
    url: "http://ghypt.hnszgh.org:9081/hn-api/getBigData/getDeptOrgInfo",
    type: "get",
    data: {
      'deptId':deptId
    },
    success: function (res) {
      // console.clear();
      // console.log("接口成功的返回：", res);
      if (res.code == 200) {
        setTotalMemberCount(res.data.totalMemberCount);
        setTotalDeptCount(res.data.totalDeptCount);
        setMemberStatistics(res.data.memberStatistics);
        setDeptCountGroup(res.data.deptCountGroup);
        setNationBar(res.data.memberNationStatistics);
        setEducation(res.data.memberEducationStatistics);
        var length = res.data.timeRangeMemberStatistics.dayMemberStatistics.length;
        if (length > 15) {
          var arr = res.data.timeRangeMemberStatistics.dayMemberStatistics;
          var start = parseInt(length / 1.5);
          arr = arr.splice(start, length);
          res.data.timeRangeMemberStatistics.dayMemberStatistics = arr;
        }
        res.data.timeRangeMemberStatistics.dayMemberStatistics = arr2Ob(res.data.timeRangeMemberStatistics.dayMemberStatistics);
        res.data.timeRangeMemberStatistics.dayMemberStatistics.max = parseInt((getMaxInArr(res.data.timeRangeMemberStatistics.dayMemberStatistics.value) / 1000) + 1) * 1200;
        res.data.timeRangeMemberStatistics.dayMemberStatistics.interval = 1000;
        // res.data.timeRangeMemberStatistics.dayMemberStatistics.max = res.data.timeRangeMemberStatistics.dayMemberStatistics.interval *numY;
        arrDay = res.data.timeRangeMemberStatistics.dayMemberStatistics;
        res.data.timeRangeMemberStatistics.monthMemberStatistics = arr2Ob(res.data.timeRangeMemberStatistics.monthMemberStatistics);
        res.data.timeRangeMemberStatistics.monthMemberStatistics.max = getMaxInArr(res.data.timeRangeMemberStatistics.monthMemberStatistics.value);
        var interval = res.data.timeRangeMemberStatistics.monthMemberStatistics.interval = getScale(res.data.timeRangeMemberStatistics.monthMemberStatistics.max, numY);
        res.data.timeRangeMemberStatistics.monthMemberStatistics.max = parseInt((res.data.timeRangeMemberStatistics.monthMemberStatistics.max / interval) + 1) * interval;
        arrMon = res.data.timeRangeMemberStatistics.monthMemberStatistics;
        setVipAddMon(res.data.timeRangeMemberStatistics.monthMemberStatistics)
        res.data.timeRangeMemberStatistics.yearMemberStatistics = arr2Ob(res.data.timeRangeMemberStatistics.yearMemberStatistics);
        res.data.timeRangeMemberStatistics.yearMemberStatistics.max = getMaxInArr(res.data.timeRangeMemberStatistics.yearMemberStatistics.value);
        res.data.timeRangeMemberStatistics.yearMemberStatistics.interval = getScale(res.data.timeRangeMemberStatistics.yearMemberStatistics.max, numY);
        res.data.timeRangeMemberStatistics.yearMemberStatistics.max = res.data.timeRangeMemberStatistics.yearMemberStatistics.interval * numY;
        arrYear = res.data.timeRangeMemberStatistics.yearMemberStatistics;
        // setVipAddYear(res.data.timeRangeMemberStatistics.yearMemberStatistics)
        setSex(res.data.memberCountBySex);
        setCity(res.data.memberInfoByCity);
        setAge(res.data.memberAgeStatistics.list);
      } else {
        alert("服务繁忙，请联系管理员")
      }

    },
    error: function (err) {
      console.log("接口失败的返回：", err);
    }

  })
}
getData();
(function () {
  setInterval(function () {
    // console.log("定时更新");
    getData();
  }, 3000000);
})();

/**
 * 格式化当前日期
 */
function getNowDate () {
  var date = new Date();
  var sign1 = "-";
  var sign2 = ":";
  var year = date.getFullYear() // 年
  var month = date.getMonth() + 1; // 月
  var day = date.getDate(); // 日
  var hour = date.getHours(); // 时
  var minutes = date.getMinutes(); // 分
  var seconds = date.getSeconds() //秒
  // var weekArr = ['星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期天'];
  // var week = weekArr[date.getDay()];
  // 给一位数数据前面加 “0”
  if (month >= 1 && month <= 9) {
    month = "0" + month;
  }
  if (day >= 0 && day <= 9) {
    day = "0" + day;
  }
  if (hour >= 0 && hour <= 9) {
    hour = "0" + hour;
  }
  if (minutes >= 0 && minutes <= 9) {
    minutes = "0" + minutes;
  }
  if (seconds >= 0 && seconds <= 9) {
    seconds = "0" + seconds;
  }
  var currentdate = year + sign1 + month + sign1 + day + " " + hour + sign2 + minutes + sign2 + seconds;
  return currentdate;
}

/**
 * 设置会员人数
 * @param {} num 
 */
function setTotalMemberCount (num) {
  var strArr = num.toString().split('').reverse();
  var htmlStr = '';
  // console.clear();
  // console.log(strArr)
  strArr.forEach(function (ele, index) {
    // console.log(ele, index)

    if ((index + 1) % 3 == 0 && index != strArr.length - 1) {
      htmlStr = '<img class="icon-comma ml-18" src="./img/comma@2x.png" alt=""><span class="VIP-item ml-18">' +
        ele +
        '</span>' + htmlStr;
    } else {
      htmlStr = '<span class="VIP-item ml-18">' +
        ele +
        '</span>' + htmlStr;
    }
  });
  $(".VIP-num").empty().append(htmlStr);

}

function setTotalDeptCount (num) {
  var strArr = num.toString().split('').reverse();
  var htmlStr = '';
  // console.clear();
  // console.log(strArr)
  strArr.forEach(function (ele, index) {
    // console.log(ele, index)
    if ((index + 1) % 3 == 0 && index != strArr.length - 1 && index != 0) {
      htmlStr = '<img class="icon-zz-comma ml-10" src="img/zz_comma@2x.png" alt="" srcset=""><div class="now-org-num-item ml-10">' +
        ele +
        '</div>' + htmlStr;
    } else {
      htmlStr = '<div class="now-org-num-item ml-10">' +
        ele +
        '</div>' + htmlStr;
    }
  });
  htmlStr = '  <span>当前组织数：</span>' + htmlStr;
  $nowOrgNum.empty().append(htmlStr);

}

/**
 * 工会组织会员数统计
 * @param {*} d 
 */
function setMemberStatistics (d) {
  var htmlStr = '';
  var val = d[0].value; //排名第一的进度条长度为满的，其他排名根据value和第一的进行比例运算
  d.forEach(function (ele, index) {
    htmlStr += '<div class="org-rank-item">' +
      '<div class="rank-num">' +
      (index + 1) +
      '</div>' +
      '<div class="org-name "><div class="line2-over" index="'+ele.deptId+'" style="z-index:1000;cursor: pointer;" onclick="aaa(event)">' +
      ele.name +
      '</div></div>' +
      '<div class="org-rank-bar ana-trs" style="width:' + ele.value / val * 0.4 * 100 + '%">' +
      '</div>' +
      '<div class="org-num ml-10">' +
      ele.value +
      '人</div>' +
      '</div>'
  })
  $vipNumCount.empty().append(htmlStr);
}
let dialog1 = document.getElementById('dialog1');
let goba = document.getElementById('goback');
let dialog = document.getElementById('dialog');
goba.onclick = function () {
  dialog1.close();
  dialog.show();
}
function aaa (e) {
  let deptId = e.target.getAttribute('index')
  dialog.show();
  let openDIa = document.getElementById('openDIa');
  openDIa.style.filter = 'blur(5px)';
  let tbody = document.getElementById('tbody');
      for(let m =0;m<tbody.childNodes.length;m++) {
        tbody.removeChild(tbody.childNodes[m])
        m--;
      }
  total = 0
  let numlist = document.getElementById('numlist');
  numlist.innerText = ' '+total+' '
  $.ajax({
    url: "http://ghypt.hnszgh.org:9081/hn-api/getBigData/getDeptMember",
    type: 'get',
    data: {
      'deptId': deptId,
      'pageIndex': '1'
    },
    success: function (res) {
      if(res.data.length) {
        total = res.data[0].total
      } else {
        total = 0
      }
      numlist.innerText = ' '+total+' '
      deptID1 = deptId
      for(let i=0;i<res.data.length;i++) {
        let tr = document.createElement('tr');
        tr.onclick = function (e) {
          dialog.close();
          dialog1.show();
          let dataList = res.data[i]
          $.ajax({
            url:"http://ghypt.hnszgh.org:9081/hn-api/getBigData/getDicts",
            type: 'get',
            data: {
              'groupCodes': 'lib_sex,lib_ethnic_group,lib_identification_type,lib_work_situation,lib_education_code,lib_household,lib_technology,lib_member_change,lib_member_change_reason,lib_political_status'
            },
            success: function (res) {
            dataList.sex = getDict(res.data,'lib_sex',dataList.sex);
            dataList.nation = getDict(res.data,'lib_ethnic_group',dataList.nation);
            dataList.certificate_type = getDict(res.data,'lib_identification_type',dataList.certificate_type);
            dataList.work_situation = getDict(res.data,'lib_work_situation',dataList.work_situation);
            dataList.education = getDict(res.data,'lib_education_code',dataList.education);
            dataList.technology_level = getDict(res.data,'lib_technology',dataList.technology_level);
            dataList.household = getDict(res.data,'lib_household',dataList.household);
            dataList.member_change = getDict(res.data,'lib_member_change',dataList.member_change);
            dataList.member_change_reason = getDict(res.data,'lib_member_change_reason',dataList.member_change_reason);
            dataList.political_status = getDict(res.data,'lib_political_status',dataList.political_status);
            dataList.birthday = dataList.birthday.slice(0,4) + '-' + dataList.birthday.slice(4,6) + '-' +dataList.birthday.slice(6,8)
            dataList.member_change_date = dataList.member_change_date.slice(0,4) + '-' + dataList.member_change_date.slice(4,6) + '-' +dataList.member_change_date.slice(6,8)

            let content = document.getElementById('content');
          let HTMLStr = `<div class="headphoto">
          <img src="" alt="" class="img">
      </div>
      <div class="info-name">${dataList.name ? dataList.name : ''}</div>
      <p class="p"><span class="span-name">性别</span><span class="span-name1">${dataList.sex ? dataList.sex : ''}</span></p>
      <p class="p"><span class="span-name">出生日期</span><span class="span-name1">${dataList.birthday ? dataList.birthday : ''}</span></p>
      <p class="p"><span class="span-name">民族</span><span class="span-name1">${dataList.nation ? dataList.nation : ''}</span></p>
      <p class="p"><span class="span-name">有效证件类型</span><span class="span-name1">${dataList.certificate_type ? dataList.certificate_type : ''}</span></p>
      <p class="p"><span class="span-name">证件号码</span><span class="span-name1">${dataList.certificate_num ? dataList.certificate_num.substring(0,3) + '*************' + dataList.certificate_num.substring(16,18) : ''}</span></p>
      <p class="p"><span class="span-name">就业状态</span><span class="span-name1">${dataList.work_situation ? dataList.work_situation : ''}</span></p>`
      content.innerHTML = HTMLStr
      let content1 = document.getElementById('content1');
      let HTMLStr1 = `<div class="text1">
      <div class="border-right"></div>
      <p class="p1"><span class="span-name2">学历</span><span class="span-name22">${dataList.education ? dataList.education : ''}</span></p>
      <p class="p1"><span class="span-name2">技术等级</span><span class="span-name22">${dataList.technology_level ? dataList.technology_level : ''}</span></p>
      <p class="p1"><span class="span-name2">移动电话</span><span class="span-name22">${dataList.mobile ? dataList.mobile.substring(0,3) + '****' + dataList.mobile.substring(7,11) : ''}</span></p>
      <p class="p1"><span class="span-name2">户籍类型</span><span class="span-name22">${dataList.household ? dataList.household : ''}</span></p>
      <p class="p1"><span class="span-name2">户籍所在地</span><span class="span-name22">${dataList.domicile ? dataList.domicile : ''}</span></p>
      <p class="p1"><span class="span-name2">会籍变化类型</span><span class="span-name22">${dataList.member_change ? dataList.member_change : ''}</span></p>
      <p class="p1"><span class="span-name2">会籍变化日期</span><span class="span-name22">${dataList.member_change_date ? dataList.member_change_date : ''}</span></p>
      <p class="p1"><span class="span-name2">会籍变化原因</span><span class="span-name22">${dataList.member_change_reason ? dataList.member_change_reason : ''}</span></p>
      <p class="p1"><span class="span-name2">政治面貌</span><span class="span-name22">${dataList.political_status ? dataList.political_status : ''}</span></p>
  </div>
  <div class="text1">
      <p class="p1"><span class="span-name2">所在单位</span><span class="span-name22">${dataList.work_unit ? dataList.work_unit : ''}</span></p>
      <p class="p1"><span class="span-name2">所在工会</span><span class="span-name22">${dataList.simplename ? dataList.simplename : ''}</span></p>
      <p class="p1"><span class="span-name2">籍贯</span><span class="span-name22">${dataList.native_place ? dataList.native_place : ''}</span></p>
      <p class="p1"><span class="span-name2">出生地</span><span class="span-name22">${dataList.homeplace ? dataList.homeplace : ''}</span></p>
  </div>`
  content1.innerHTML = HTMLStr1
            }
          })
        }
        let td = document.createElement('td');
        td.innerText = i+1
        tr.appendChild(td)
        let td1 = document.createElement('td');
        td1.innerText = res.data[i].name
        tr.appendChild(td1)
        let td2 = document.createElement('td');
        td2.innerText = res.data[i].simplename
        tr.appendChild(td2)
        let td3 = document.createElement('td');
        td3.innerText = res.data[i].p_dept_name
        tr.appendChild(td3)
        tbody.appendChild(tr)
      }
    },
    error: function (err) {
      console.log("接口失败的返回：", err);
    }
  })
}
let ret = document.getElementById('ret')
ret.onclick = function () {
  let openDIa = document.getElementById('openDIa');
  openDIa.style.filter = '';
  let dialog = document.getElementById('dialog');
  dialog.close();
  flag = 1
  for(var m=2;m<7;m++) {
    ullist[m].innerText = m -1
    ullist[m].className = 'li'
  }
  ullist[2].className = 'li11'
}
let ret1 = document.getElementById('ret1')
ret1.onclick = function () {
  let openDIa = document.getElementById('openDIa');
  openDIa.style.filter = '';
  let dialog1 = document.getElementById('dialog1');
  dialog1.close();
  flag = 1
  for(var m=2;m<7;m++) {
    ullist[m].innerText = m -1
    ullist[m].className = 'li'
  }
  ullist[2].className = 'li11'
}

function getDict (val,item,itemval) {
  for(var j=0;j<val.length;j++) {
    if(val[j].group_code == item) {
      let aa = val[j].dict
      for(let i=0;i<aa.length;i++) {
        if(aa[i].code == itemval) {
          return aa[i].name
          break;
        }
      }
    }
  }
}
/**
 * 设置工会组织数统计
 * @param {*} params 
 */
function setDeptCountGroup (d) {
  var htmlStr = '';
  var val = d[0].value; //排名第一的进度条长度为满的，其他排名根据value和第一的进行比例运算
  d.forEach(function (ele, index) {
    htmlStr += '<div class="org-rank-item">' +
      '<div class="rank-num">' +
      (index + 1) +
      '</div>' +
      '<div class="org-name "><div class="line2-over" style="font-size:12px;">' +
      ele.name +
      '</div></div>' +
      '<div class="org-rank-bar ana-trs" style="width:' + ele.value / val * 0.4 * 100 + '%">' +
      '</div>' +
      '<div class="org-num ml-10">' +
      ele.value +
      '</div>' +
      '</div>'
  })
  $orgCount.empty().append(htmlStr);
}
/**
 * 民族排序
 * @param {*} d 
 */
function setNationBar (d) {
  var htmlStr = '';
  var sum = 0; //总数
  d.forEach(function (ele) {
    sum += ele.value;
  })
  d.forEach(function (ele, index) {
    htmlStr += ' <div class="item-nation">' +
      '<div class="name-nation">' +
      ele.name + '（' + ele.value + '）' +
      '</div>' +
      '<div class="con-progress">' +
      '<div class="bar ana-trs" style="right:' + (100 - (ele.value / sum * 100)) + '%">' +

      '</div>' +
      '</div>' +
      '</div>'
  })
  Nation.setOption({
    series: [{
      name: '民族占比',
      type: 'pie',
      data: d,
      itemStyle: {
        normal: {
          color: function (params) {
            // console.log("颜色参数：", params.dataIndex);
            var colArr = ["#61B0F4", "#6B95E2", "#7CF092", "#E8C164", "#F26E83"];
            return colArr[params.dataIndex];
          }
        }
      },
      label: {
        formatter: function (params) {
          // console.log("参数：",params)
          return '{a| ' + params.data.name + '}{b|' + (params.data.value / sum * 100).toFixed(2) + '%}';
        },
        rich: {
          a: {
            color: '#fff',
            fontSize: Fs16
          },
          b: {
            color: '#eed735',
            fontWeight: 'bolder',
            fontSize: Fs16
          }
        }
      },
      center: ['50%', '50%'],
      radius: [0, '50%']
    }]
  })
  $conNation.empty().append(htmlStr)
}

/**
 * 设置学历比例
 * @param {*} d 
 */
function setEducation (d) {
  var htmlStr = '';
  var sum = 0; //总数
  d.forEach(function (ele) {
    sum += ele.value;
  })
  d.forEach(function (ele, index) {
    htmlStr += ' <div class="edu-item">' +
      '<div class="edu-name">' +
      ele.name +
      '</div>' +
      '<div class="edu-bar ana-trs" style="width:' + ele.value / sum * 100 * 0.6 + '%">' +

      '</div>' +
      '<div class="edu-p">' +
      (ele.value / sum * 100).toFixed(1) + '%' +
      '</div>' +
      '</div>';
  })
  $education.empty().append(htmlStr);
}



/**
 * 设置月增长
 * @param {*} d 
 */
function setVipAddDay (d) {
  $('.active-add').removeClass('active-add');
  $('.day-add').addClass('active-add');
  VipAdd.setOption({
    grid: {
      top: '3%',
      height: '80%',
      width: '90%'
    },
    xAxis: [{


      type: 'category',
      data: d.name,
      axisPointer: {
        type: 'shadow'
      },
      axisLabel: {
        color: '#59D5FF',
        fontSize: Fs16,
        interval: 0,
        nameLocation: 'start',
        formatter: function (value, index) {
          return parseInt(value.substring(value.length - 2, value.length)) + "日"
        }
      }
    }],
    yAxis: [{
      type: 'value',
      min: 0,
      max: d.max,
      interval: d.interval,
      axisLabel: {
        color: '#65C6E7'
        ,
        formatter: function (value, index) {
          if (value >= 10000 && value < 10000000) {
            value = value / 10000 + "万";
          } else if (value >= 10000000) {
            value = value / 10000000 + "千万";
          }
          return value;
        }
      }
    }],
    series: [{
      name: '人次',
      type: 'bar',
      data: d.value,
      itemStyle: {
        color: '#1AE0FE'
      },
      barWidth: trainBarW,
      label: {
        show: true,
        position: 'top'
      }

    },
    {
      type: 'line',
      symbol: 'circle',
      data: d.value,
      lineStyle: {
        color: '#21D6DF'
      },
      itemStyle: {
        borderColor: '#23ffff',
        color: '#23ffff'
      }
    }
    ]
  })
}

/**
 * 设置月增长
 * @param {*} d 
 */
function setVipAddMon (d) {
  $('.active-add').removeClass('active-add');
  $('.mon-add').addClass('active-add');
  VipAdd.setOption({
    grid: {
      top: '3%',
      height: '80%',
      width: '90%'
    },
    xAxis: [{


      type: 'category',
      data: d.name,
      axisPointer: {
        type: 'shadow'
      },
      axisLabel: {
        color: '#59D5FF',
        fontSize: Fs16,
        interval: 0,
        rotate: 20,
        nameLocation: 'start',
        formatter: function (value, index) {
          return parseInt(value.substring(value.length - 2, value.length)) + "月"
        }
      },
      axisTick: {
        inside: false,
        length: 5,
        lineStyle: {
          color: '#00f',
          shadowColor: '#00f',
          shadowOffsetY: -5
        }
      }
    }],
    yAxis: [{
      type: 'value',
      min: 0,
      max: d.max,
      interval: d.interval,
      axisLabel: {
        color: '#65C6E7',
        formatter: function (value, index) {
          if (value >= 10000 && value < 10000000) {
            value = value / 10000 + "万";
          } else if (value >= 10000000) {
            value = value / 10000000 + "千万";
          }
          return value;
        }
      }
    }],
    series: [{
      name: '人次',
      type: 'bar',
      data: d.value,
      itemStyle: {
        color: '#1AE0FE'
      },
      barWidth: trainBarW,
      label: {
        show: true,
        position: 'top'
      }

    },
    {
      type: 'line',
      symbol: 'circle',
      data: d.value,
      lineStyle: {
        color: '#21D6DF'
      },
      itemStyle: {
        borderColor: '#23ffff',
        color: '#23ffff'
      }
    }
    ]
  })
}

/**
 * 设置年增长
 * @param {*} d 
 */
function setVipAddYear (d) {
  $('.active-add').removeClass('active-add');
  $('.year-add').addClass('active-add');
  VipAdd.setOption({
    grid: {
      top: '3%',
      height: '80%',
      width: '90%'
    },
    xAxis: [{


      type: 'category',
      data: d.name,
      axisPointer: {
        type: 'shadow'
      },
      axisLabel: {
        color: '#59D5FF',
        fontSize: Fs16,
        interval: 0,
        rotate: 20,
        nameLocation: 'start',
        formatter: function (value, index) {
          return value + "年"
        }
      }
    }],
    yAxis: [{
      name: '人',
      // nameLocation:"middle",
      type: 'value',
      min: 0,
      max: d.max,
      interval: d.interval,
      axisLabel: {
        color: '#65C6E7',
        formatter: function (value, index) {
          if (value >= 10000 && value < 10000000) {
            value = value / 10000 + "万";
          } else if (value >= 10000000) {
            value = value / 10000000 + "千万";
          }
          return value;
        }
      },
      splitLine: {
        show: true
      }
    }],
    series: [{
      name: '人',
      type: 'bar',
      data: d.value,
      itemStyle: {
        color: '#1AE0FE'
      },
      barWidth: trainBarW * 2,
      label: {
        show: true
      }

    },
    {
      type: 'line',
      symbol: 'circle',
      data: d.value,
      lineStyle: {
        color: '#21D6DF',
        postion: {
          top: 30
        }
      },
      itemStyle: {
        borderColor: '#23ffff',
        color: '#23ffff'
      }
    }
    ]
  })
}



/*===============处理数据=============*/
/**
 * 数组转对象
 * @param {Array} d 
 */
function arr2Ob (d) {
  var ob = {
    "value": [],
    "name": []
  }
  for (var i = 0; i < d.length; i++) {
    ob.value.push(d[i].value);
    ob.name.push(d[i].name);
  }
  return ob;
}

/**
 * 获取两个数中的最大值
 * @param {*} a 
 * @param {*} b 
 */
function getMax (a, b) {
  // return a>=b?a:b;
  return Math.max(a, b);
}

/**
 * 找出索引数组中的最大值
 * @param {*} d 
 */
function getMaxInArr (d) {
  return Math.max.apply(null, d)
}

/**
 * 获取图表刻度
 * @param {*} d：图表中的最大值 
 * @param {*} n：将坐标轴分成几个刻度
 */
function getScale (d, n) {
  var interval = Math.ceil(d / n);
  var interval = (Math.ceil(interval / 10000) + 1) * 10000; //对刻度进行整10处理
  return interval;
}


/*===========监听事件=========*/
$("body").on("click", ".style-add", function () {
  // console.log($(this).index())
  switch ($(this).index()) {
    case 1:
      setVipAddDay(arrDay);
      break;
    case 2:
      setVipAddMon(arrMon);
      break;
    case 3:
      setVipAddYear(arrYear);
      break;
    default:
      setVipAddMon(arrMon);
      break;
  }
})

// actChart.setOption({
//     grid: {
//         left: '30%',
//         top: 0,
//         height:'100%'
//     },
//     xAxis: {
//         type: 'value',
//         name: 'Days',
//         show: false,
//         max: function (value) {
//             return value.max * 1.5;
//         }
//     },
//     yAxis: {

//         type: 'category',
//         inverse: true,
//         data: ['海1航集团有限公司', '海2南华信国际控股有限公司', '中3国石化海南炼油化工有油化工油化工', '中4国石化海南炼油化工有油化工油化工', '中5国石化海南炼油化工有油化工油化工', '中6国石化海南炼油化工有油化工油化工', '中7国石化海南炼油化工有油化工油化工', '中8国石化海南炼油化工有油化工油化工', '中9国石化海南炼油化工有油化工油化工', '中10国石化海南炼油化工有油化工油化工'],
//         show: true,
//         axisLine: {
//             show: false
//         },
//         axisTick: {
//             show: false,

//         },
//         axisLabel: {
//             interval:0,
//             fontSize: topTextSize,
//             lineHeight: topTextSize,
//             color: "#fff",
//             formatter: function (value, index) {
//                 // console.log("活动互动", value, index);
//                 if(value.length>=12){
//                     value = value.slice(0,8) + '...' 
//                 }

//                 var strs = value.split(''); //字符串数组
//                 var str = ''
//                 for (var i = 0, s; s = strs[i++];) { //遍历字符串数组
//                     str += s;
//                     if (!(i % 6)){//按需要求余
//                         str += '\n' ; 
//                         // if(i>=6){
//                         //     break
//                         // }

//                     } 
//                 }
//                 console.log("活动互动",index,str)
//                 var i;
//                 switch (index) {
//                     case 0:
//                         i = 'a';
//                         break;
//                     case 1:
//                         i = 'b';
//                         break;
//                     case 2:
//                         i = 'c';
//                         break;
//                     default:
//                         i = 'd';
//                         break;
//                 }
//                 // return '{'+i+'|'+(index+1)+'}'+"  "+value;
//                 return '{' + i + '|' + (index + 1) + '}' + "  " + '{ts|' + str + '}';
//             },
//             rich: {
//                 a: {
//                     width: topIconW,
//                     height: topIconH,
//                     align: 'center',
//                     backgroundColor: '#B81226'
//                 },
//                 b: {
//                     width: topIconW,
//                     height: topIconH,
//                     align: 'center',
//                     backgroundColor: '#E35915'
//                 },
//                 c: {
//                     width: topIconW,
//                     height: topIconH,
//                     align: 'center',
//                     backgroundColor: '#FFBE2C'
//                 },
//                 d: {
//                     width: topIconW,
//                     height: topIconH,
//                     align: 'center',
//                     backgroundColor: '#1DAED2'
//                 },
//                 ts: {
//                     width: textWidth,
//                     height: textHeight,
//                     fontSize: topTextSize,
//                     lineHeight: topTextSize,
//                     align:'center'
//                 }
//             }
//         },
//     },
//     series: [{
//         type: 'bar',
//         data: [7919, 5965, 3879],
//         barWidth: barWidth,
//         itemStyle: {
//             color: new echarts.graphic.LinearGradient(
//                 0, 0, 1, 0,
//                 [{
//                         offset: 0,
//                         color: '#1E87F3'
//                     },
//                     {
//                         offset: 1,
//                         color: '#17D5F1'
//                     }
//                 ]
//             )
//         },
//         label: {
//             show: true,
//             position: 'right',
//             formatter: function (e) {
//                 console.log("活动互动：", e);
//                 return e.data + "人";
//             },
//             fontSize: actLabelFs,
//             color: '#FFF45C'
//         }
//     }]
// })

// function showAct(d) {
//     $('#num-act-field').text(d.total_times); //活动总场数
//     $('#num-act-people').text(d.total_participation_number); //活动的参与人数
//     actChart.setOption({
//         grid: {
//             left: '30%',
//             top: actMT
//         },
//         xAxis: {
//             type: 'value',
//             name: 'Days',
//             show: false,
//             max: function (value) {
//                 return value.max * 1.5;
//             }
//         },
//         yAxis: {

//             type: 'category',
//             inverse: true,
//             data: d.dataListSort.name,
//             show: true,

//             axisLine: {
//                 show: true,
//                 symbolSize: [10, 15]
//             },
//             axisTick: {
//                 show: false,
//                 interval: 1,
//                 length: 10,
//                 lineStyle: {
//                     color: "#fff"
//                 }

//             },
//             axisLabel: {

//                 fontSize: topTextSize,
//                 color: "#fff",
//                 formatter: function (value, index) {
//                     // console.log("活动互动", value, index);
//                     var i;
//                     switch (index) {
//                         case 0:
//                             i = 'a';
//                             break;
//                         case 1:
//                             i = 'b';
//                             break;
//                         case 2:
//                             i = 'c';
//                             break;
//                     }
//                     return '{' + i + '|}' + "  " + value;
//                 },
//                 rich: {
//                     a: {
//                         // padding: [10, 0, 10, 0],
//                         width: topIconW,
//                         height: topIconH,
//                         align: 'center',
//                         backgroundColor: {
//                             image: topIcon.a
//                         }
//                     },
//                     b: {
//                         width: topIconW,
//                         height: topIconH,
//                         align: 'center',
//                         backgroundColor: {
//                             image: topIcon.b
//                         }
//                     },
//                     c: {
//                         width: topIconW,
//                         height: topIconH,
//                         align: 'center',
//                         backgroundColor: {
//                             image: topIcon.c
//                         }
//                     }
//                 }
//             },
//         },
//         series: [{
//                 type: 'bar',
//                 data: d.dataListSort.times,
//                 itemStyle: {
//                     color: '#f7cf5e'
//                 },
//                 label: {
//                     show: true,
//                     position: 'right',
//                     formatter: function (e) {
//                         // console.log("活动互动：", e);
//                         return e.data + "场次活动";
//                     },
//                     fontSize: actLabelFs
//                 }
//             },
//             {
//                 type: 'bar',
//                 data: d.dataListSort.members,
//                 itemStyle: {
//                     color: '#6894f8'
//                 },
//                 label: {
//                     show: true,
//                     position: 'right',
//                     formatter: function (e) {
//                         // console.log("活动互动：", e);
//                         return e.data + "人参与";
//                     },
//                     fontSize: actLabelFs
//                 }
//             }

//         ]
//     })

// }

function setCity (data) {
  var sum = 0;
  $(data).each(function () {
    sum += this.value
  });
  CR.setOption({
    series: [{
      name: '户籍分布',
      type: 'pie',
      data: data,
      itemStyle: {
        normal: {
          color: function (params) {
            // console.log("颜色参数：", params.dataIndex);
            var colArr = ["#61B0F4", "#6B95E2", "#7CF092", "#E8C164", "#F26E83", "#61B0F4", "#6B95E2", "#7CF092", "#61B0F4", "#6B95E2", "#7CF092"];
            return colArr[params.dataIndex];
          }
        }
      },
      label: {
        show: true,
        formatter: function (params) {
          // console.log("参数：",params)
          return '{a|' + params.data.name + '}{b|' + (params.data.value / sum * 100).toFixed(2) + '%}';
        },
        rich: {
          a: {
            color: '#fff',
            fontSize: Fs16
          },
          b: {
            color: '#eed735',
            fontWeight: 'bolder',
            fontSize: Fs16
          }
        }
      },
      center: ['50%', '50%'],
      radius: [0, '65%']
    }]
  })
}
function setSex (data) {
  var sum = 0;
  $(data).each(function () {
    sum += this.value
  });
  vipSex.setOption({
    series: [{
      name: '户籍分布',
      type: 'pie',
      data: data,
      itemStyle: {
        normal: {
          color: function (params) {
            // console.log("颜色参数：", params.dataIndex);
            var colArr = ["#61B0F4", "#F26E83"];
            return colArr[params.dataIndex];
          }
        }
      },
      label: {
        show: true,
        formatter: function (params) {
          // console.log("参数：",params)
          return '{b|' + (params.value / sum * 100).toFixed(2) + '%}{a|\n' + params.name + '}';
        },
        rich: {
          a: {
            color: '#fff',
            fontSize: Fs16
          },
          b: {
            color: '#eed735',
            fontWeight: 'bolder',
            fontSize: Fs16
          }
        }
      },
      center: ['50%', '50%'],
      radius: [0, '65%']
    }]
  })
}

function setAge (data) {
  var sum = 0;
  $(data).each(function () {
    sum += this.value
  });
  vipAge.setOption({
    series: [{
      name: '户籍分布',
      type: 'pie',
      data: data,
      itemStyle: {
        normal: {
          color: function (params) {
            // console.log("颜色参数：", params.dataIndex);
            var colArr = ["#61B0F4", "#6B95E2", "#7CF092", "#E8C164", "#F26E83", "#61B0F4", "#6B95E2", "#7CF092", "#61B0F4", "#6B95E2", "#7CF092"];
            return colArr[params.dataIndex];
          }
        }
      },
      label: {
        show: true,
        formatter: function (params) {
          // console.log("参数：",params)
          return '{a| ' + params.name + '}{b|' + (params.value / sum * 100).toFixed(2) + '%}';
        },
        rich: {
          a: {
            color: '#fff',
            fontSize: Fs16
          },
          b: {
            color: '#eed735',
            fontWeight: 'bolder',
            fontSize: Fs16
          }
        }
      },
      center: ['50%', '50%'],
      radius: [0, '65%']
    }]
  })
}





var mySwiper = new Swiper('.con-vip-nation', {
  loop: true,

  // 如果需要分页器
  pagination: {
    el: '.swiper-pagination',
  },
})
var mySwiper1 = new Swiper('.con-vip-age', {
  loop: true,

  // 如果需要分页器
  pagination: {
    el: '.swiper-pagination',
  },
})






