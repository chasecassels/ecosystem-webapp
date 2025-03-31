<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
        <html>
        <head>
            <title>Canadian Terrestrial Ecozone Database</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" href="test.css">
        </head>

        <body style="font-family: monospace ;background-color:beige">
            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: darkgreen">
                    <div>
                        <a href=/simple/list  class="navbar-brand" > Canadian Terrestrial Ecozone Database </a> <a style="margin-left: 55em" class="navbar-brand"  href="about">About</a>
                    </div>
                </nav>
            </header>
            <br>
        
        	<div class="row">
                <div class="container">
                    <h3 class="text-center" style="font-size: 30px"> ${ecozone.name} </h3>
                    
                    
                                
                    <hr>
                    
                        <div class="topnav">
                    
                    <div> <br>
			          
			          Population density: ${(Math.round(ecozone.population/ecozone.totalArea * 1000))/1000} per km<sup>2</sup>
			          <br> <br>
			          Percentage of Canada's total land area: ${(Math.round(ecozone.totalArea/9985000*100000))/1000}%
			          <br> <br>
			          Percentage of Canada's Population: ${(Math.round(ecozone.population/40000000*100000))/1000}%
			          
			          </div>
                 </div>
             </div>
        </div>
        
            
        </body>
        
        

        </html>