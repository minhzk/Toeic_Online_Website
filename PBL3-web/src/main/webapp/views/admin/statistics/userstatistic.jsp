<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
    <div class="container">
        <canvas id="myChart"style="max-width: 800px; max-height: 600px;"></canvas>
    </div>
    <script>
        let myChart = document.getElementById('myChart').getContext('2d');

        let massPopChart = new Chart(myChart, {
            type:'bar', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
            data:{
                labels:['Giới tính nam', 'Giới tính nữ', 'Chưa xác định giới tính'],
                datasets:[{
                    label:'Giới tính',
                    data:[
                        ${maleCount},
                        ${femaleCount},
                        ${nullGender}
                    ],
                    //backgroundColor:'green',
                    backgroundColor:[
                        'rgba(255, 99, 132, 0.6)',
                        'rgba(54, 162, 235, 0.6)',
                        'rgba(24, 153, 20, 0.6)'
                    ],
                    borderWidth:1,
                    borderColor:'#777',
                    hoverBorderWidth:3,
                    hoverBorderColor:'#000'
                }]
            },
            options:{
                plugins: {
                    title: {
                        display: true,
                        text: 'Biểu đồ cột số lượng người dùng theo độ giới tính',
                        fontSize: 30
                    }
                },
                legend: {
                    display: true,
                    position: 'right',
                    labels: {
                        fontColor: '#000000'
                    }
                },
                layout: {
                    padding: {
                        left: 100,
                        right: 0,
                        bottom: 0,
                        top: 100
                    }
                },
                tooltips: {
                    enabled: true
                },
                scales: {
                    y: {
                        beginAtZero: true,
                        suggestedMin: 0 // Thêm thiết lập này để đảm bảo trục y bắt đầu từ giá trị 0
                    }
                }
            }
        });
    </script>

</body>
</html>