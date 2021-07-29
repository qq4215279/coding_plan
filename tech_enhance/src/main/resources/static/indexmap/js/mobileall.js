let id = location.search.split('=')[1]

let back = document.getElementById('back');
let choose1 = document.getElementById('choose1');
let choose2 = document.getElementById('choose2');
let poeplenum = document.getElementById('peoplenum');
let orgNum = document.getElementById('orgNum');
let list = document.getElementById('list');

$.ajax({
    url: "http://ghypt.hnszgh.org:9081/hn-api/getBigData/getDeptOrgInfo",
    type: "get",
    data: {
    },
    success: function (res) {
        let peoplenumber = res.data.totalMemberCount;
        let orgnumber = res.data.totalDeptCount;
        let peoplenumberAll = res.data.memberStatistics;
        let orgnumberAll = res.data.deptCountGroup;
        let flag = peoplenumberAll[0].value;
        let flag1 = orgnumberAll[0].value;
        if(id == '1') {
            choose1.className = 'span1 choosed';
            choose2.className = 'span1';
            poeplenum.innerHTML = '工会会员人数：'+ peoplenumber +' &nbsp;人'
            orgNum.innerText = '会员数';
            for(let i=0;i<peoplenumberAll.length;i++){
                let div = document.createElement('div');
                div.className = 'org-all';
                let div1 = document.createElement('div');
                div1.className = 'num';
                div1.innerHTML = i+1;
                div.appendChild(div1);
                let div2 = document.createElement('div');
                if(peoplenumberAll[i].name.length <= 8) {
                    div2.style.lineHeight = '4.047vh';
                }
                div2.className = 'name';
                div2.innerHTML = peoplenumberAll[i].name;
                div.appendChild(div2);
                let div3 = document.createElement('div');
                div3.className = 'bar';
                let timer = setInterval(() => {
                    aa = div3.style.width.split('v')[0]*1;
                    if (peoplenumberAll[i].value == 0) {
                        div3.style.width = 0;
                        clearInterval(timer)
                    }
                    else if(aa >= peoplenumberAll[i].value / flag * 26.666) {
                        clearInterval(timer)
                    } else {
                        div3.style.width = aa <= peoplenumberAll[i].value / flag * 26.666 ? aa + 1 +'vw' : peoplenumberAll[i].value / flag * 26.666 +'vw'
                    }
                }, 30);
                // div3.style.width = peoplenumberAll[i].value / flag * 26.666 + 'vw';
                    div.appendChild(div3);
                let div4 = document.createElement('div');
                div4.className = 'peoplenumber';
                div4.innerHTML = peoplenumberAll[i].value + ' 人';
                div.appendChild(div4);
                list.appendChild(div);
            }
        } else if (id == '2') {
            choose1.className = 'span1';
            choose2.className = 'span1 choosed';
            poeplenum.innerHTML = '工会组织数：'+ orgnumber +' &nbsp;个'
            orgNum.innerText = '组织数';
            for(let i=0;i<orgnumberAll.length;i++){
                let div = document.createElement('div');
                div.className = 'org-all';
                let div1 = document.createElement('div');
                div1.className = 'num';
                div1.innerHTML = i+1;
                div.appendChild(div1);
                let div2 = document.createElement('div');
                if(orgnumberAll[i].name.length <= 8) {
                    div2.style.lineHeight = '4.047vh';
                }
                div2.className = 'name';
                div2.innerHTML = orgnumberAll[i].name;
                div.appendChild(div2);
                let div3 = document.createElement('div');
                div3.className = 'bar';
                let timer = setInterval(() => {
                    aa = div3.style.width.split('v')[0]*1;
                    if (orgnumberAll[i].value == 0) {
                        div3.style.width = 0;
                        clearInterval(timer)
                    }
                    else if(aa >= orgnumberAll[i].value / flag1 * 26.666) {
                        clearInterval(timer)
                    } else {
                        div3.style.width = aa <= orgnumberAll[i].value / flag1 * 26.666 ? aa + 1 +'vw' : orgnumberAll[i].value / flag1 * 26.666 +'vw'
                    }
                }, 30);
                // div3.style.width = orgnumberAll[i].value / flag1 * 26.666 + 'vw';
                div.appendChild(div3);
                let div4 = document.createElement('div');
                div4.className = 'peoplenumber';
                div4.innerHTML = orgnumberAll[i].value + ' ';
                div.appendChild(div4);
                list.appendChild(div);
            }
        }
        choose1.onclick = function () {
            for(let n=7;n<list.childNodes.length;n++) {
                list.removeChild(list.childNodes[n])
                n = n-1
            }
            choose1.className = 'span1 choosed';
            choose2.className = 'span1';
            poeplenum.innerHTML = '工会会员人数：'+ peoplenumber +' &nbsp;人';
            orgNum.innerText = '会员数';
            for(let i=0;i<peoplenumberAll.length;i++){
                let div = document.createElement('div');
                div.className = 'org-all';
                let div1 = document.createElement('div');
                div1.className = 'num';
                div1.innerHTML = i+1;
                div.appendChild(div1);
                let div2 = document.createElement('div');
                if(peoplenumberAll[i].name.length <= 8) {
                    div2.style.lineHeight = '4.047vh';
                }
                div2.className = 'name';
                div2.innerHTML = peoplenumberAll[i].name;
                div.appendChild(div2);
                let div3 = document.createElement('div');
                div3.className = 'bar';
                let timer = setInterval(() => {
                    aa = div3.style.width.split('v')[0]*1;
                    // console.log(peoplenumberAll[i].value / flag)
                    if (peoplenumberAll[i].value == 0) {
                        div3.style.width = 0;
                        clearInterval(timer)
                    }
                    else if(aa >= peoplenumberAll[i].value / flag * 26.666) {
                        clearInterval(timer)
                    }  else {
                        div3.style.width = aa <= peoplenumberAll[i].value / flag * 26.666 ? aa + 1 +'vw' : peoplenumberAll[i].value / flag * 26.666 +'vw'
                    }
                }, 30);
                // div3.style.width = peoplenumberAll[i].value / flag * 26.666 + 'vw';
                div.appendChild(div3);
                let div4 = document.createElement('div');
                div4.className = 'peoplenumber';
                div4.innerHTML = peoplenumberAll[i].value + ' 人';
                div.appendChild(div4);
                list.appendChild(div);
            }
        };
        choose2.onclick = function () {
            for(let n=7;n<list.childNodes.length;n++) {
                list.removeChild(list.childNodes[n])
                n = n-1
            }
            choose2.className = 'span1 choosed';
            choose1.className = 'span1';
            poeplenum.innerHTML = '工会组织数：'+ orgnumber +' &nbsp;个'
            orgNum.innerText = '组织数';
            for(let i=0;i<orgnumberAll.length;i++){
                let div = document.createElement('div');
                div.className = 'org-all';
                let div1 = document.createElement('div');
                div1.className = 'num';
                div1.innerHTML = i+1;
                div.appendChild(div1);
                let div2 = document.createElement('div');
                if(orgnumberAll[i].name.length <= 8) {
                    div2.style.lineHeight = '4.047vh';
                }
                div2.className = 'name';
                div2.innerHTML = orgnumberAll[i].name;
                div.appendChild(div2);
                let div3 = document.createElement('div');
                div3.className = 'bar';
                let timer = setInterval(() => {
                    aa = div3.style.width.split('v')[0]*1;
                    
                    if (orgnumberAll[i].value == 0) {
                        div3.style.width = 0;
                        clearInterval(timer)
                    }
                    else if(aa >= orgnumberAll[i].value / flag1 * 26.666) {
                        clearInterval(timer)
                    } else {
                        div3.style.width = aa <= orgnumberAll[i].value / flag1 * 26.666 ? aa + 1 +'vw' : orgnumberAll[i].value / flag1 * 26.666 +'vw'
                    }
                }, 30);
                // div3.style.width = orgnumberAll[i].value / flag1 * 26.666 + 'vw';
                div.appendChild(div3);
                let div4 = document.createElement('div');
                div4.className = 'peoplenumber';
                div4.innerHTML = orgnumberAll[i].value + ' ';
                div.appendChild(div4);
                list.appendChild(div);
            }
        };
    },
    error: function (err) {
    console.log("接口失败的返回：", err);
    }
})


back.onclick = function () {
    location.href = './data-mobile.html'
}