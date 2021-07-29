let total1 = document.getElementById('total1');
let total2 = document.getElementById('total2');
let btn1 = document.getElementById('btn1');
let btn2 = document.getElementById('btn2');

$.ajax({
    url: "http://ghypt.hnszgh.org:9081/hn-api/getBigData/getDeptOrgInfo",
    type: "get",
    data: {
    },
    success: function (res) {
        let list1 = res.data.totalMemberCount.toString().split('').reverse()
        let list3 = res.data.totalDeptCount.toString().split('').reverse()
        let list2 = []
        let list4 = []
        for(let i=0;i<list1.length;i++) {
            if((i)%3 || i==0) {
                list2.push(list1[i])
            } else {
                list2.push(',')
                list2.push(list1[i])
            }
        }
        list2 = list2.reverse()
        for(var j=0;j<list2.length;j++){
            if(list2[j]!=',') {
                let abc = list2[j]
                let spanaa = document.createElement('span');
                spanaa.className = 'num'
                spanaa.innerText = 0
                total1.appendChild(spanaa);
                let timer = setInterval(() => {
                    if(abc == spanaa.innerHTML*1) {
                        clearInterval(timer)
                    } else {
                        spanaa.innerHTML = spanaa.innerHTML*1 + 1
                    }
                }, 80);
            } else {
                let spanaa = document.createElement('span');
                spanaa.className = 'dou'
                spanaa.innerText = list2[j]
                total1.appendChild(spanaa);
            }
        }
        for(let n=0;n<list3.length;n++) {
            if((n)%3 || n==0) {
                list4.push(list3[n])
            } else {
                list4.push(',')
                list4.push(list3[n])
            }
        }
        list4 = list4.reverse()
        for(var j=0;j<list4.length;j++){
            if(list4[j]!=',') {
                let abc = list4[j]
                let spanaa = document.createElement('span');
                spanaa.className = 'num'
                spanaa.innerText = 0
                total2.appendChild(spanaa);
                let timer = setInterval(() => {
                    if(abc == spanaa.innerHTML*1) {
                        clearInterval(timer)
                    } else {
                        spanaa.innerHTML = spanaa.innerHTML*1 + 1
                    }
                }, 80);
            } else {
                let spanaa = document.createElement('span');
                spanaa.className = 'dou'
                spanaa.innerText = list4[j]
                total2.appendChild(spanaa);
            }
        }
        let spanaa = document.createElement('span');
        spanaa.innerText = '人'
        total1.appendChild(spanaa);
        let spanaa1 = document.createElement('span');
        spanaa1.innerText = '个'
        total2.appendChild(spanaa1);
        
    },
    error: function (err) {
    console.log("接口失败的返回：", err);
    }
})

btn1.onclick = function () {
    location.href = './data-mobileall.html?index=1'
}
btn2.onclick = function () {
    location.href = './data-mobileall.html?index=2'
}