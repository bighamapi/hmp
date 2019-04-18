/**
 * 日期操作相关函数
 */

function dateTimeFormatter (date ,format) {
    // 时间格式化辅助函数 date:毫秒数 format:'yyyy-MM-dd hh:mm:ss'
    if (!date || date == "") {
        return ""
    }

    if (typeof date === "string") {
        var mts = date.match(/(\/Date\((\d+)\)\/)/);
        if (mts && mts.length >= 3) {
            date = parseInt(mts[2])
        }
    }

    date = new Date(date);
    if (!date || date.toUTCString() == "Invalid Date") {
        return ""
    }

    var map = {
        "M": date.getMonth() + 1, //月份
        "d": date.getDate(), //日
        "h": date.getHours(), //小时
        "m": date.getMinutes(), //分
        "s": date.getSeconds(), //秒
        "q": Math.floor((date.getMonth() + 3) / 3), //季度
        "S": date.getMilliseconds() //毫秒
    };

    format = format.replace(/([yMdhmsqS])+/g, function(all, t){
        var v = map[t];
        if(v !== undefined){
            if(all.length > 1){
                v = '0' + v;
                v = v.substr(v.length-2)
            }
            return v
        }
        else if(t === 'y'){
            return (date.getFullYear() + '').substr(4 - all.length)
        }
        return all
    });

    return format
}


    function isEqualDateStr (dateStr1, dateStr2) {
        let dateArr1 = dateStr1.split('/');
        let dateArr2 = dateStr2.split('/');
        if (parseInt(dateArr1[0], 10) !== parseInt(dateArr2[0], 10)) {
            return false
        }
        if (parseInt(dateArr1[1], 10) !== parseInt(dateArr2[1], 10)) {
            return false
        }
        if (parseInt(dateArr1[2], 10) !== parseInt(dateArr2[2], 10)) {
            return false
        }
        return true
    }
     
     
    /**
     * 格式化时间转变为： 2017年10月10日 10时10分10秒
     * @param date 2017/10/10
     * @param mat_arr   [年，月，日 ....]  非必填
     * @returns {string} 返回格式化好的时间格式
     */
    function data_format(date,_mat_arr) {
        //默认格式
        let mat_arr = ['年','月','日'];
        if(_mat_arr){
            mat_arr = _mat_arr;
        }
        if (!date || date == "") {
            return ""
        }
        if (typeof date === "string") {
            var mts = date.match(/(\/Date\((\d+)\)\/)/);
            if (mts && mts.length >= 3) {
                date = parseInt(mts[2])
            }
        }
        date = new Date(date);
        if (!date || date.toUTCString() == "Invalid Date") {
            return ""
        }
        var map = {
            "Y": date.getFullYear(), //年份
            "M": date.getMonth() + 1, //月份
            "d": date.getDate(), //日
            "h": date.getHours(), //小时
            "m": date.getMinutes(), //分
            "s": date.getSeconds() //秒
        };
        //月份补位
        if(map.M && map.M<10){
            map.M = '0'+map.M;
        }
        //time格式化
        let str = '';
        mat_arr.forEach((item,index)=>{
            switch (index){
                case 0:
                    str+=`${map.Y}${item}`;break;
                case 1:
                    str+=`${map.M}${item}`;break;
                case 2:
                    str+=`${map.d}${item}`;break;
                case 3:
                    str+=` ${map.h}${item}`;break;
                case 4:
                    str+=`${map.m}${item}`;break;
                case 5:
                    str+=`${map.s}${item}`;break;
            }
        });
        return str;
    }
