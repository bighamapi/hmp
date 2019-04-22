function drawLine1(item){
        //line.showLoading();
        // 绘制图表
        return {
          title: {
              text: item.text
          },
          tooltip: {
              trigger: 'axis',
              axiosPointer:{
                type:'cross',
                label:{
                  backgroundColor: '#6a7985'
                }
              }
          },
          legend: {//数据项
              data: item.legend,
          },
          grid: {
              left: '3%',
              right: '4%',
              bottom: '3%',
              containLabel: true
          },
          toolbox: {
              feature: {
                  saveAsImage: {}
              }
          },
          xAxis: {
              type: 'category',
              boundaryGap: false,
              data: item.category
          },
          yAxis: {
              type: 'value'
          },
          series: item.data
      }
       
      //line.hideLoading();
    }

    function drawPie1(){
        return {
            title: {
                text: '访问来源',
                left: 'center',
                top: 20,
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['直接访问','邮件营销','搜索引擎']
            },
            series : [
                {
                    name:'访问来源',
                    type:'pie',
                    radius : '55%',
                    roseType: 'angle',
                    center: ['50%', '50%'],
                    data:[
                        {value:335, name:'直接访问'},
                        {value:310, name:'邮件营销'},
                        // {value:274, name:'联盟广告'},
                        // {value:235, name:'视频广告'},
                        {value:400, name:'搜索引擎'}
                    ].sort(function (a, b) { return a.value - b.value; }),
                    roseType: 'radius',
                    labelLine: {
                        normal: {
                            smooth: 0.2,
                            length: 10,
                            length2: 20
                        }
                    },
                    itemStyle: {
                        // 阴影的大小
                        shadowBlur: 200,
                        // 阴影水平方向上的偏移
                        shadowOffsetX: 0,
                        // 阴影垂直方向上的偏移
                        shadowOffsetY: 0,
                        // 阴影颜色
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    },
                    animationType: 'scale',
                    animationEasing: 'elasticOut',
                    animationDelay: function (idx) {
                        return Math.random() * 200;
                    }
                }
            ]
        };
    }