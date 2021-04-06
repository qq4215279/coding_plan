(function () {
    /*===========初始化=========*/
    //新媒体轮播
    var mySwiper = new Swiper('.swiper-container', {
        loop: false,

        // 如果需要分页器
        pagination: {
            el: '.swiper-pagination',
        },
    })

    var w = document.documentElement.clientWidth || document.body.clientWidth;
    var h = document.documentElement.clientHeight || document.body.clientHeight;
    var Fs50 = w * 0.00895;
    var Fs58 = w * 0.01038;
    var Fs42 = w * 0.00752;
    var Fs45 = w * 0.00805;
    var Fs12 = w * 0.00625;
    var Fs14 = w * 0.00747;
    var Fs18 = w * 0.00952;

    //地图
    var myChart = echarts.init(document.getElementById('main'));
    var geoJson = HNdata;
    myChart.hideLoading();
    var mapW = w * 0.33;
    var mapH = h * 0.47;
    echarts.registerMap('HN', geoJson);

    //工会新媒体
    var newmChart = echarts.init(document.getElementById('newm')); //轮播1
    var newsChart = echarts.init(document.getElementById('news')); //轮播2

    //教育培训
    var numY = 6; //Y周的刻度线个数
    var trainBarW = w * 0.0077; //教育培训条形图宽度
    var trainChart = echarts.init(document.getElementById('train'));

    //普惠商城
    var mallChart = echarts.init(document.getElementById('mall'));
    var mallTextFs = w * 0.00645;
    var mallNumFs = w * 0.00903; //数量字体大小
    var mallLegendFs = w * 0.00781; //legend字体大小
    var mallCenterTextFs = w * 0.00698; //交易金额字体大小
    var mallCenterNumFs = w * 0.011; //交易金额数目字体大小

    //活动互动
    var topTextSize = w * 0.00895; //top文字大小
    var topIconW = w * 0.03742; //top图标宽度
    var topIconH = w * 0.01199; //top图标高度
    var actMT = h * 0.08929; //活动图表的上外边距
    var actLabelFs = w * 0.00752; //label字体大小
    var topIcon = {
        'a': './img/top1.png',
        'b': './img/top2.png',
        'c': './img/top3.png'
    }
    var actChart = echarts.init(document.getElementById('act'));

    //维权咨询
    var personW = w * 0.0179; //小人的宽
    var personH = h * 0.08458; //小人的高
    var personM = w * 0.012535; //小人间距
    var presonML = w * 0.00841; //小人的左外边距
    var consultChart = echarts.init(document.getElementById('consult'));

    //困难帮扶
    var $nhw = "#num-hard-worker"; //困难职工总数量
    var $nhwd = "#nhw-d"; //困难职工总数每日数量
    var $nhww = "#nhw-w"; //困难职工总数每周数量
    var $nhwm = "#nhw-m"; //困难职工总数每月数量
    var $ha = "#help-amount"; //帮扶金额
    var $had = "#ha-d"; //帮扶金额每日数量
    var $haw = "#ha-w"; //帮扶金额每周数量
    var $ham = "#ha-m"; //帮扶金额每月数量
    var $nhot = "#num-hotline"; //12351热线办理总数量
    var $nhotd = "#nhot-d"; //12351热线办理每日数量
    var $nhotw = "#nhot-w"; //12351热线办理每周数量
    var $nhotm = "#nhot-m"; //12351热线办理每月数量
    var $nmh = "#num-medical-help"; //医疗互助总数量
    var $nmhd = "#nmh-d"; //医疗互助每日数量
    var $nmhw = "#nmh-w"; //医疗互助每周数量
    var $nmhm = "#nmh-m"; //医疗互助每月数量

    //组织建设
    var $norg = "#num-org"; //组织个数
    var $nvip = "#num-vip"; //会员人数
    var $nadd = "#num-add"; //新增人数
    var $ntr = "#num-transfer"; //转会人数
    var $naddc = "#num-addcom"; //新增企业建会人数

    //当前日期
    var $date = "#date";
    setInterval(function () {
        $($date).text(getNowDate());
    }, 1000)

    //天气
    var $weather = $("#weather");
    var $iconWeather = $("#icon-weather");
    var $liveWe = $("#liveWeather");
    var url_W = "https://restapi.amap.com/v3/weather/weatherInfo?key=1d72067abfecd34b5bf2f865c1a6804f&city=460100&extensions=all"; //天气预报URL
    var url_w02 = "https://restapi.amap.com/v3/weather/weatherInfo?key=1d72067abfecd34b5bf2f865c1a6804f&city=460100"; //实时天气预报
    var weather_cycle = 3600000; //天气数据请求的周期

    var ttt1 = 1500;
    var ttt2 = 3000;
    var ttt3 = 4500;
    var ttt4 = 6000;
    var ttt5 = 7500;
    var ttt6 = 8000;

    var t = 3000; //控制数字相加时间
    var text_ = 20 + Math.round(Math.random() * (5 - 1)); //随机生成数字
    var ctl_id = "num-hard-worker"; //获取数字页面id
    var Num = 6808; //起始值

    showMap();
    getData();
    setInterval(function () {
        getData();
    }, ttt1+ttt2+ttt3+ttt4+ttt5+ttt6+1000)

    getLiveWeather();
    getWeatherData();
    //每隔一端时间请求一次天气数据
    setInterval(function () {
        getLiveWeather();
        getWeatherData();
    }, weather_cycle)

    /*==============监听事件==============*/



    /*================获取数据==========*/
    function getData() {
        $.ajax({
            url: "http://ghypt.hnszgh.org:9081/hn-api/getBigData",
            type: "POST",
            data: {},
            success: function (res) {
                //  console.clear();
                console.info("接口成功的返回：", res);
                var code = res.code;
                var data = res.data;
                if (code == 200) {

                    data.hd.dataListSort = arr2Ob(data.hd.dataListSort);
                    data.px.dataListSort = arr2Ob(data.px.dataListSort);
                    data.px.max = getMax(getMaxInArr(data.px.dataListSort.times), getMaxInArr(data.px.dataListSort.members));
                    data.px.interval = getScale(data.px.max, numY);
                    data.px.max = data.px.interval * numY;
                    showNewM1(data.newMediaData); //工会新媒体
                    showNewM2(data.newMediaData); //工会新媒体
                    setTimeout(function () {
                        showAct(data.hd); //活动互动
                    }, ttt1)
                    setTimeout(function () {
                        showTrain(data.px); //培训
                    }, ttt2)
                    setTimeout(function () {
                        showMall(data.hpMallData); //商城
                    }, ttt3)
                    setTimeout(function () {
                        showConsult(data.seekLegalAdviceData); //维权
                    }, ttt4)
                    setTimeout(function () {
                        /*困难*/
                        Refresh($nhw, data.helpData.kunNan.total_count);
                        Refresh($nhwd, data.helpData.kunNan.daily_count);
                        Refresh($nhww, data.helpData.kunNan.week_count);
                        Refresh($nhwm, data.helpData.kunNan.month_count);
                        Refresh($ha, data.helpData.bangFu.total_count);
                        Refresh($had, data.helpData.bangFu.daily_count);
                        Refresh($haw, data.helpData.bangFu.week_count);
                        Refresh($ham, data.helpData.bangFu.month_count);
                        Refresh($nhot, data.helpData.reXian.total_count);
                        Refresh($nhotd, data.helpData.reXian.daily_count);
                        Refresh($nhotw, data.helpData.reXian.week_count);
                        Refresh($nhotm, data.helpData.reXian.month_count);
                        Refresh($nmh, data.helpData.yiLiao.total_count);
                        Refresh($nmhd, data.helpData.yiLiao.daily_count);
                        Refresh($nmhw, data.helpData.yiLiao.week_count);
                        Refresh($nmhm, data.helpData.yiLiao.month_count);
                    }, ttt5)
                    setTimeout(function () {
                        $.ajax({
                            url: "http://ghypt.hnszgh.org:9081/hn-api/getBigData/getDeptOrgInfo",
                            type: "get",
                            data: {
                            },
                            success: function (res) {
                                if (res.code == 200) {
                                    Refresh($norg, res.data.totalDeptCount);
                                    Refresh($nvip, res.data.totalMemberCount);
                                }
                            }
                        })
                        /**组织建设 */
                        
                        Refresh($nadd, data.organizationData.addition_member_count);
                        Refresh($ntr, data.organizationData.transfer_member_count);
                        Refresh($naddc, data.organizationData.new_member_company_established);
                    }, ttt6)

                } else {
                    alert(服务繁忙);
                }
            },
            error: function (err) {
                console.log("接口失败的返回：", err);
            }

        })
    }

    //获取天气预报数据
    function getWeatherData() {
        $.ajax({
            url: url_W,
            type: "GET",
            success: function (res) {
                console.clear();
                console.log("天气：", res);
                if (res.status == "1") {
                    var data_w = res.forecasts[0].casts[0];
                    $weather.text(data_w.daytemp + "℃-" + data_w.nighttemp + "℃");

                }

            },
            error: function (err) {
                console.log("失败：", err);
            }
        })
    }

    /**
     * 获取实时天气数据
     */
    function getLiveWeather() {
        $.ajax({
            url: url_w02,
            type: "GET",
            success: function (res) {
                console.clear();
                console.log("天气：", res);
                if (res.status == "1") {
                    var data_w = res.lives[0];
                    $liveWe.text(data_w.weather);
                    $iconWeather.addClass(getIconWeather(data_w.weather));
                }

            },
            error: function (err) {
                console.log("失败：", err);
            }
        })
    }

    /*===============处理数据=============*/
    /**
     * 数组转对象
     * @param {Array} d 
     */
    function arr2Ob(d) {
        var ob = {
            "times": [],
            "members": [],
            "name": []
        }
        for (var i = 0; i < d.length; i++) {
            ob.times.push(d[i].times);
            ob.members.push(d[i].members);
            ob.name.push(d[i].name);
        }
        return ob;
    }

    /**
     * 获取两个数中的最大值
     * @param {*} a 
     * @param {*} b 
     */
    function getMax(a, b) {
        // return a>=b?a:b;
        return Math.max(a, b);
    }

    /**
     * 找出索引数组中的最大值
     * @param {*} d 
     */
    function getMaxInArr(d) {
        return Math.max.apply(null, d)
    }

    /**
     * 获取图表刻度
     * @param {*} d：图表中的最大值 
     * @param {*} n：将坐标轴分成几个刻度
     */
    function getScale(d, n) {
        var interval = Math.ceil(d / n);
        var interval = (Math.ceil(interval / 10) + 1) * 10; //对刻度进行整10处理
        return interval;
    }

    /**
     * 保留N位小数且不进行四舍五入
     * @param {*} d：需要保留的原始数字
     * @param {*} n：需要保留的位数
     */
    function retainDecimal(d, n) {
        var w = n * 10;
        return Math.floor(d * w) / w;
    }

    /**
     * 格式化当前日期
     */
    function getNowDate() {
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
     * 根据天气现象名称返回对应的图标
     * @param {*} d 
     */
    function getIconWeather(d) {
        var iconClass;
        switch (d) {
            case "晴":
                iconClass = "icon-tianqi1"
                break;
            case "多云":
                iconClass = "icon-tianqi"
                break;
            case "阴":
                iconClass = "icon-yin"
                break;
            case "阵雨":
                iconClass = "icon-zhenyu"
                break;
            case "雷阵雨":
                iconClass = "icon-leizhenyu"
                break;
            case "雷阵雨并伴有冰雹":
                iconClass = "icon-leizhenyubingbanyoubingbao"
                break;
            case "雨夹雪":
                iconClass = "icon-406"
                break;
            case "小雨":
                iconClass = "icon-xiaoyu"
                break;
            case "中雨":
                iconClass = "icon-zhongyu"
                break;
            case "大雨":
                iconClass = "icon-rain_level_05"
                break;
            case "暴雨":
                iconClass = "icon-baoyu"
                break;
            case "大暴雨":
                iconClass = "icon-weather_dabaoyu"
                break;
            case "特大暴雨":
                iconClass = "icon-18"
                break;
            case "阵雪":
                iconClass = "icon-t16"
                break;
            case "小雪":
                iconClass = "icon-scouther_icon"
                break;
            case "中雪":
                iconClass = "icon-zhongxue"
                break;
            case "大雪":
                iconClass = "icon-weather_daxue"
                break;
            case "暴雪":
                iconClass = "icon-snow_level_07"
                break;
            case "雾":
                iconClass = "icon-wu"
                break;
            case "冻雨":
                iconClass = "icon-dongyu"
                break;
            case "沙尘暴":
                iconClass = "icon-shachenbao-"
                break;
            case "小雨-中雨":
                iconClass = "icon-xiaoyuzhongyu"
                break;
            case "中雨-大雨":
                iconClass = "icon-zhongyu-dayu"
                break;
            case "大雨-暴雨":
                iconClass = "icon-w-23"
                break;
            case "暴雨-大暴雨":
                iconClass = "icon-w-24"
                break;
            case "大暴雨-特大暴雨":
                iconClass = "icon-w-25"
                break;
            case "小雪-中雪":
                iconClass = "icon-xiaoxue-zhongxue"
                break;
            case "中雪-大雪":
                iconClass = "icon-zhongxue-daxue"
                break;
            case "大雪-暴雪":
                iconClass = "icon-daxue-baoxue"
                break;
            case "浮尘":
                iconClass = "icon-fuchen"
                break;
            case "扬沙":
                iconClass = "icon-yangsha"
                break;
            case "强沙尘暴":
                iconClass = "icon-qiangshachenbao"
                break;
            case "飑":
                iconClass = "icon-biao"
                break;
            case "龙卷风":
                iconClass = "icon-longjuanfeng"
                break;
            case "弱高吹雪":
                iconClass = "icon-ruogaochuixue"
                break;
            case "轻霾":
                iconClass = "icon-qingmai-"
                break;
            case "霾":
                iconClass = "icon-icon-haze"
                break;
            default:
                iconClass = ""
                break;
        }
        return iconClass;
    }

    /*===============渲染数据=============*/
    //数字变动动画
    function Refresh(el, num) {
        $(el).text(parseFloat(num).toLocaleString());
        // document.getElementById(el).innerHTML = parseFloat(num).toLocaleString(); //给数字负值并加逗号

    }

    //渲染地图
    function showMap() {
        myChart.setOption(option = {
            tooltip: {
                trigger: 'item',
                formatter: function (params) {
                    //回调函数，参数params具体格式参加官方API
                    //鼠标放到某个地市上，显示这个地市的名称加人口数
                    //例如 params.name：当前地市名称。params.value：你传入的json数据与当前地市匹配的一项。
                    //params.data.value:你传入的json数据与当前地市匹配的一项中'value'对应的数据
                    // return params.name+":"+params.data.value;
                }
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['海南'],
                show: false
            },
            geo: {
                id: 0,
                map: 'HN'
            },
            // visualMap: {
            //     min: 0,
            //     max: 2500,
            //     left: 'left',
            //     top: 'bottom',
            //     text: ['高', '低'], // 文本，默认为数值文本
            //     calculable: true
            // },
            series: [{
                    name: "海南",
                    type: 'map',
                    width: mapW,
                    height: mapH,
                    map: 'HN',
                    label: {
                        normal: {
                            show: true,
                            textStyle: {
                                color: '#fff',
                                fontSize: Fs45
                            }
                        },
                        emphasis: {
                            show: true
                        },
                        color: '#fff'
                    },
                    itemStyle: {
                        areaColor: '#00213c',
                        borderColor: '#21f9fa'
                    },
                    // data: [{
                    //     name: '三亚市',
                    //     value: 10
                    // }]
                },
                {
                    name: '地点',
                    type: 'effectScatter',
                    coordinateSystem: 'geo',
                    zlevel: 2,
                    rippleEffect: {
                        brushType: 'stroke'
                    },
                    label: {
                        emphasis: {
                            show: true,
                            position: 'right',
                            formatter: '{b}'
                        }
                    },
                    symbolSize: 2,
                    showEffectOn: 'render',
                    itemStyle: {
                        normal: {
                            color: '#46bee9'
                        }
                    },
                    data: [{
                            "name": "儋州市",
                            "value": [109.1591,
                                19.5953
                            ],
                            "symbolSize": 12,
                            "itemStyle": {
                                "normal": {
                                    "color": "#3afffe"
                                }
                            }
                        },
                        {
                            "name": "陵水黎族自治县",
                            "value": [110.03,
                                18.5415
                            ],
                            "symbolSize": 12,
                            "itemStyle": {
                                "normal": {
                                    "color": "#3afffe"
                                }
                            }
                        },
                        {
                            "name": "三亚市",
                            "value": [109.05819519042976,
                                18.18081759203312
                            ],
                            "symbolSize": 12,
                            "itemStyle": {
                                "normal": {
                                    "color": "#3afffe"
                                }
                            }
                        },
                        {
                            "name": "文昌市",
                            "value": [111.5, 19.8564],
                            "symbolSize": 12,
                            "itemStyle": {
                                "normal": {
                                    "color": "#3afffe"
                                }
                            }
                        },
                        {
                            "name": "保亭黎族苗族自治县",
                            "value": [109.6284,
                                18.6108
                            ],
                            "symbolSize": 12,
                            "itemStyle": {
                                "normal": {
                                    "color": "#3afffe"
                                }
                            }
                        },
                        {
                            "name": "乐东黎族自治县",
                            "value": [108.5283,
                                18.6301
                            ],
                            "symbolSize": 12,
                            "itemStyle": {
                                "normal": {
                                    "color": "#3afffe"
                                }
                            }
                        },
                        {
                            "name": "东方市",
                            "value": [108.2,
                                18.85
                            ],
                            "symbolSize": 12,
                            "itemStyle": {
                                "normal": {
                                    "color": "#3afffe"
                                }
                            }
                        },
                        {
                            "name": "昌江黎族自治县",
                            "value": [108.43,
                                19.2
                            ],
                            "symbolSize": 12,
                            "itemStyle": {
                                "normal": {
                                    "color": "#3afffe"
                                }
                            }
                        },
                        {
                            "name": "白沙黎族自治县",
                            "value": [108.95,
                                19.07
                            ],
                            "symbolSize": 12,
                            "itemStyle": {
                                "normal": {
                                    "color": "#3afffe"
                                }
                            }
                        },
                        {
                            "name": "临高县",
                            "value": [109.6957,
                                19.8063
                            ],
                            "symbolSize": 12,
                            "itemStyle": {
                                "normal": {
                                    "color": "#3afffe"
                                }
                            }
                        },
                        {
                            "name": "澄迈县",
                            "value": [110.22,
                                19.7314
                            ],
                            "symbolSize": 12,
                            "itemStyle": {
                                "normal": {
                                    "color": "#3afffe"
                                }
                            }
                        },
                        {
                            "name": "海口市",
                            "value": [111.1,
                                19.8516
                            ],
                            "symbolSize": 12,
                            "itemStyle": {
                                "normal": {
                                    "color": "#3afffe"
                                }
                            }
                        },
                        {
                            "name": "定安县",
                            "value": [110.84,
                                19.6698
                            ],
                            "symbolSize": 12,
                            "itemStyle": {
                                "normal": {
                                    "color": "#3afffe"
                                }
                            }
                        },
                        {
                            "name": "屯昌县",
                            "value": [110.277,
                                19.282
                            ],
                            "symbolSize": 12,
                            "itemStyle": {
                                "normal": {
                                    "color": "#3afffe"
                                }
                            }
                        },
                        {
                            "name": "琼中黎族苗族自治县",
                            "value": [109.9913,
                                18.9
                            ],
                            "symbolSize": 12,
                            "itemStyle": {
                                "normal": {
                                    "color": "#3afffe"
                                }
                            }
                        },
                        {
                            "name": "琼海市",
                            "value": [111.0208,
                                19.1
                            ],
                            "symbolSize": 12,
                            "itemStyle": {
                                "normal": {
                                    "color": "#3afffe"
                                }
                            }
                        },
                        {
                            "name": "万宁市",
                            "value": [110.8137,
                                18.6388
                            ],
                            "symbolSize": 12,
                            "itemStyle": {
                                "normal": {
                                    "color": "#3afffe"
                                }
                            }
                        },
                        {
                            "name": "五指山市",
                            "value": [109.1282,
                                18.8599
                            ],
                            "symbolSize": 12,
                            "itemStyle": {
                                "normal": {
                                    "color": "#3afffe"
                                }
                            }
                        }
                    ]
                },

                {
                    name: '数据流动',
                    type: 'lines',
                    coordinateSystem: 'geo',
                    zlevel: 2,
                    large: true,
                    geoIndex: 0,
                    effect: {
                        show: true,
                        period: 3,
                        constantSpeed: 50,
                        symbol: 'pin',
                        symbolSize: 10,
                        trailLength: 0
                    },
                    lineStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: '#3afffe'
                        }, {
                            offset: 1,
                            color: '#3afffe'
                        }], false),
                        width: 2,
                        opacity: 0.2,
                        curveness: 0.1
                    },
                    data: [{

                            coords: [
                                [107.8498,
                                    18.85
                                ],
                                [111.1,
                                    19.8516
                                ]

                            ]
                        },
                        {

                            coords: [
                                [110.8137,
                                    18.6388
                                ],
                                [111.1,
                                    19.8516
                                ]

                            ]
                        },
                        {

                            coords: [
                                [109.9913,
                                    18.9
                                ],
                                [111.1,
                                    19.8516
                                ]

                            ]
                        },
                        {

                            coords: [

                                [111.0208,
                                    19.1
                                ],
                                [111.1,
                                    19.8516
                                ]
                            ]
                        },
                        {

                            coords: [

                                [110.277,
                                    19.282
                                ],
                                [111.1,
                                    19.8516
                                ]
                            ]
                        },

                        {

                            coords: [

                                [109.1282,
                                    18.8599
                                ],
                                [111.1,
                                    19.8516
                                ]
                            ]
                        },

                    ]
                }
            ]
        });
    }

    //渲染工会新媒体1
    function showNewM1(d) {
        newmChart.setOption({
            legend: {
                bottom: 10,
                left: 'center',
                data: [{
                        name: '网站访问人数',
                        icon: 'diamond'
                    },
                    {
                        name: '微信公众号粉丝',
                        icon: 'diamond'
                    },
                    {
                        name: 'APP注册人数',
                        icon: 'diamond'
                    },
                    {
                        name: 'APP访问量',
                        icon: 'diamond'
                    }
                ],
                textStyle: {
                    color: '#fff',
                    fontSize: Fs14
                }
            },
            series: [{
                name: '工会新媒体',
                type: 'pie',
                data: [{
                        value: d.website_view_count,
                        name: '网站访问人数',
                        itemStyle: {
                            color: '#249dc5'
                        }
                    },
                    {
                        value: d.wechat_fans_count,
                        name: '微信公众号粉丝',
                        itemStyle: {
                            color: '#496dc2'
                        }
                    },
                    {
                        value: d.app_signUp_count,
                        name: 'APP注册人数',
                        itemStyle: {
                            color: '#36c9be'
                        }
                    },
                    {
                        value: d.app_visit_count,
                        name: 'APP访问量',
                        itemStyle: {
                            color: '#d48ba5'
                        }
                    }
                ],
                label: {
                    formatter: function (params) {
                        // console.log("参数：",params)
                        return '{a|' + params.data.name + '}{b|' + params.data.value + '}';
                    },
                    rich: {
                        a: {
                            color: '#fff',
                            fontSize: Fs14
                        },
                        b: {
                            color: '#eed735',
                            fontWeight: 'bolder',
                            fontSize: Fs18
                        }
                    }
                },
                center: ['50%', '40%'],
                radius: [0, '65%']
            }]
        })

    }
    //渲染工会新媒体2
    function showNewM2(d) {
        newsChart.setOption({
            legend: {
                bottom: 10,
                left: 'center',
                data: [{
                        name: '发布量',
                        icon: 'diamond'
                    },
                    {
                        name: '评论量',
                        icon: 'diamond'
                    },
                    {
                        name: '点击量',
                        icon: 'diamond'
                    },
                    {
                        name: '阅读量',
                        icon: 'diamond'
                    }
                ],
                textStyle: {
                    color: '#fff',
                    fontSize: Fs14
                }
            },
            series: [{
                name: '工会新闻',
                type: 'pie',
                data: [{
                        value: d.publish_count,
                        name: '发布量',
                        itemStyle: {
                            color: '#249dc5'
                        }
                    },
                    {
                        value: d.comment_count,
                        name: '评论量',
                        itemStyle: {
                            color: '#496dc2'
                        }
                    },
                    {
                        value: d.click_count,
                        name: '点击量',
                        itemStyle: {
                            color: '#36c9be'
                        }
                    },
                    {
                        value: d.read_count,
                        name: '阅读量',
                        itemStyle: {
                            color: '#d48ba5'
                        }
                    }
                ],
                label: {
                    formatter: function (params) {
                        // console.log("参数：",params)
                        return '{a|' + params.data.name + '}{b|' + params.data.value + '}';
                    },
                    rich: {
                        a: {
                            color: '#fff',
                            fontSize: Fs14
                        },
                        b: {
                            color: '#eed735',
                            fontWeight: 'bolder',
                            fontSize: Fs18
                        }
                    }
                },
                center: ['50%', '40%'],
                radius: [0, '65%']
            }]
        })
    }

    //渲染教育培训
    function showTrain(d) {
        trainChart.setOption({
            legend: {
                // zlevel:1000,
                // bottom:10,
                // left:'center',
                data: [{
                        name: '场次',
                        icon: 'diamond'
                    },
                    {
                        name: '人次',
                        icon: 'diamond'
                    }
                ],
                formatter: function (params) {
                    // console.log("培训：", params);
                    if (params == '场次') {
                        return '{a|培训共}{b|' + d.total_times + '}{a|场次}';
                    } else {
                        return '{a|培训共}{b|' + d.total_participation_number + '}{a|人次}';
                    }

                },
                textStyle: {
                    rich: {
                        a: {
                            color: '#fff',
                            fontSize: Fs14
                        },
                        b: {
                            color: '#eed735',
                            fontWeight: 'bolder',
                            fontSize: Fs18
                        }
                    }
                }

            },
            xAxis: [{
                type: 'category',
                data: d.dataListSort.name,
                axisPointer: {
                    type: 'shadow'
                },
                axisLabel: {
                    color: '#fff',
                    fontSize: Fs12
                }
            }],
            yAxis: [{
                type: 'value',
                min: 0,
                max: d.max,
                interval: d.interval,
                axisLabel: {
                    color: '#fff'
                }
            }],
            series: [{
                    name: '场次',
                    type: 'bar',
                    data: d.dataListSort.times,
                    itemStyle: {
                        color: '#b0504d'
                    },
                    barWidth: trainBarW,
                    label: {
                        show: true,
                        position: 'top',
                        fontSize: Fs12
                    }
                },
                {
                    name: '人次',
                    type: 'bar',
                    data: d.dataListSort.members,
                    itemStyle: {
                        color: '#7b3dc2'
                    },
                    barWidth: trainBarW,
                    label: {
                        show: true,
                        position: 'top',
                        fontSize: Fs12
                    }
                },
                {
                    type: 'line',
                    symbol: 'circle',
                    data: d.dataListSort.members,
                    lineStyle: {
                        color: '#245f8a'
                    },
                    itemStyle: {
                        borderColor: '#23ffff',
                        color: '#23ffff'
                    }
                }
            ]
        })
    }

    //渲染普惠商城
    function showMall(d) {
        mallChart.setOption({
            legend: {
                data: [{
                        name: '普惠覆盖人数',
                        icon: 'diamond'
                    },
                    {
                        name: '商城交易数量',
                        icon: 'diamond'
                    },
                    {
                        name: '进驻商户数量',
                        icon: 'diamond'
                    },
                    {
                        name: '商品数量',
                        icon: 'diamond'
                    }
                ],
                // right: '4.32%',
                bottom: '2%',
                // orient: 'vertical',
                formatter: function (params) {
                    // console.log("培训：", params);
                    // if (params == '覆盖人数') {
                    //     return '{a|普惠覆盖人数}';
                    // } else {
                    //     return '{a|商城交易数量}';
                    // }
                    return '{a|' + params + '}';

                },
                textStyle: {
                    rich: {
                        a: {
                            color: '#fff',
                            fontSize: mallLegendFs
                        },
                        b: {
                            color: '#edd647',
                            fontWeight: 'bolder',
                            fontSize: mallNumFs
                        }
                    }
                }
            },
            series: [{
                type: 'pie',
                radius: ['50%', '70%'],
                avoidLabeOverlap: false,
                label: {
                    normal: {
                        show: true,
                        // position: 'center',
                        formatter: function (params) {
                            // console.log("参数：", params)
                            return '{a|' + params.data.name + '}{b|' + params.data.value + '}';
                        },
                        rich: {
                            a: {
                                color: '#fff',
                                fontSize: mallTextFs
                            },
                            b: {
                                color: '#edd647',
                                fontWeight: 'bolder',
                                fontSize: mallNumFs
                            }
                        }
                    },
                    emphasis: {
                        show: true,
                        position: 'center',
                        textStyle: {
                            fontSize: '30',
                            fontWeight: 'bold'
                        }
                    }
                },
                data: [{
                        value: d.cover_member_count,
                        name: '普惠覆盖人数',
                        itemStyle: {
                            color: '#46cdfc'
                        }
                    },
                    {
                        value: d.trade_count,
                        name: '商城交易数量',
                        itemStyle: {
                            color: '#4bcfbf'
                        }
                    },
                    {
                        value: d.locate_vendor,
                        name: '进驻商户数量',
                        itemStyle: {
                            color: '#d4819e'
                        }
                    },
                    {
                        value: d.product_count,
                        name: '商品数量',
                        itemStyle: {
                            color: '#ffbd3f'
                        }
                    },
                    {
                        value: 0,
                        name: '交易金额',
                        label: {
                            show: true,
                            position: 'center',
                            formatter: function (e) {
                                // console.log("交易金额：", e)
                                return '{a|' + e.name + '}\n{b|￥' + d.total_amount + '}';
                            },
                            rich: {
                                a: {
                                    color: '#3afffe',
                                    fontSize: mallCenterTextFs,

                                },
                                b: {
                                    color: '#edd647',
                                    fontWeight: 'bolder',
                                    fontSize: mallCenterNumFs
                                }
                            }
                        }
                    }
                ]
            }]
        })
    }

    //渲染活动互动
    function showAct(d) {
        $('#num-act-field').text(d.total_times); //活动总场数
        $('#num-act-people').text(d.total_participation_number); //活动的参与人数
        actChart.setOption({
            grid: {
                left: '30%',
                top: actMT
            },
            xAxis: {
                type: 'value',
                name: 'Days',
                show: false,
                max: function (value) {
                    return value.max * 1.5;
                }
            },
            yAxis: {

                type: 'category',
                inverse: true,
                data: d.dataListSort.name,
                show: true,

                axisLine: {
                    show: true,
                    symbolSize: [10, 15]
                },
                axisTick: {
                    show: false,
                    interval: 1,
                    length: 10,
                    lineStyle: {
                        color: "#fff"
                    }

                },
                axisLabel: {

                    fontSize: topTextSize,
                    color: "#fff",
                    formatter: function (value, index) {
                        // console.log("活动互动", value, index);
                        var i;
                        switch (index) {
                            case 0:
                                i = 'a';
                                break;
                            case 1:
                                i = 'b';
                                break;
                            case 2:
                                i = 'c';
                                break;
                        }
                        return '{' + i + '|}' + "  " + value;
                    },
                    rich: {
                        a: {
                            // padding: [10, 0, 10, 0],
                            width: topIconW,
                            height: topIconH,
                            align: 'center',
                            backgroundColor: {
                                image: topIcon.a
                            }
                        },
                        b: {
                            width: topIconW,
                            height: topIconH,
                            align: 'center',
                            backgroundColor: {
                                image: topIcon.b
                            }
                        },
                        c: {
                            width: topIconW,
                            height: topIconH,
                            align: 'center',
                            backgroundColor: {
                                image: topIcon.c
                            }
                        }
                    }
                },
            },
            series: [{
                    type: 'bar',
                    data: d.dataListSort.times,
                    itemStyle: {
                        color: '#f7cf5e'
                    },
                    label: {
                        show: true,
                        position: 'right',
                        formatter: function (e) {
                            // console.log("活动互动：", e);
                            return e.data + "场次活动";
                        },
                        fontSize: actLabelFs
                    }
                },
                {
                    type: 'bar',
                    data: d.dataListSort.members,
                    itemStyle: {
                        color: '#6894f8'
                    },
                    label: {
                        show: true,
                        position: 'right',
                        formatter: function (e) {
                            // console.log("活动互动：", e);
                            return e.data + "人参与";
                        },
                        fontSize: actLabelFs
                    }
                }
                
            ]
        })

    }

    //渲染维权咨询
    function showConsult(d) {
        var maxData = d.inProcess_count + d.toDo_count + d.total_count; //图形最大数为以下三者之和
        $("#num-solve-problem").text(d.total_count); //已帮助总人数
        $("#num-now").text(d.inProcess_count); //处理中
        $("#num-un").text(d.toDo_count); //未办理
        consultChart.setOption({
            grid: {
                left: presonML
            },
            xAxis: {
                max: maxData,
                show: false
            },
            yAxis: {
                data: [''],
                inverse: true,
                show: false
            },
            series: [{
                    type: 'pictorialBar',
                    symbol: 'image://./img/person.png',
                    symbolRepeat: 'fixed',
                    symbolMargin: '35%',
                    symbolClip: true,
                    symbolSize: [personW, personH],
                    symbolBoundingData: maxData,
                    data: [d.total_count],
                    z: 10
                },
                {
                    type: 'pictorialBar',
                    animationDuration: 0,
                    symbolRepeat: 'fixed',
                    symbolMargin: '35%',
                    symbol: 'image://./img/bgp.png',
                    symbolSize: [personW, personH],
                    symbolBoundingData: maxData,
                    data: [maxData]
                }
            ]
        })
    }

    // //渲染困难帮扶
    // function showNum(n,el){

    // }
    /*===========错误处理============*/
})()