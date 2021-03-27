<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ include file="./include/inc_header.jsp" %>     

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="./index.css">
</head>
<body>
    <div>
        <div class="calc-header">
            <input type="text" id="inval">
        </div>
        <div class="calc-body">
            <div>
                <table>
                    <tbody>
                        <tr class="number">
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><button value="/">/</button></td>
                        </tr>
                        <tr class="number">
                            <td><button value="7">7</button></td>
                            <td><button value="8">8</button></td>
                            <td><button value="9">9</button></td>
                            <td><button value="*">*</button></td>
                        </tr>
                        <tr class="number">
                            <td><button value="4">4</button></td>
                            <td><button value="5">5</button></td>
                            <td><button value="6">6</button></td>
                            <td><button value="-">-</button></td>
                        </tr>
                        <tr class="number">
                            <td><button value="1">1</button></td>
                            <td><button value="2">2</button></td>
                            <td><button value="3">3</button></td>
                            <td><button value="+">+</button></td>
                        </tr>
                        <tr>
                            <td colspan="3"><button value="0">0</button></td>
                            <td><button value="=" id="result-btn">=</button></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="./index.js"></script>
</html>

<script>
$(function()){
	$buttons.click(function()){
		var newVal=$(this).val();
		
	}
	
	
	
});

</script>




