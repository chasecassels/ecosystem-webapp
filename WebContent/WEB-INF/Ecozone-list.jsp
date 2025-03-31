<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
        <html>
        <head>
            <title>Canadian Terrestrial Ecozone Database</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" href="test.css">
            <link rel="icon" href="https://ca.nfis.org/favicon.ico">
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
                    <h3 class="text-center" style="font-size: 30px"> Ecozones</h3>
                    <hr>
             
                     <div class="topnav">
                     
                     
					  
					 <form name="search" method="post" action="search">
					    <input placeholder= "Search.." type="text" name="input"/> 
					    <input type="submit" value="Go" /> <a style="float: right;" href="new" class="btn btn-info">Add New Ecozone</a>
					    <div> <a style= "font-size: 12px" > Search by Province, Tree Genus, or Zone Name </a> </div>
					    
					 </form>	  
					</div> 
                    
                    
                    <table style= "font-size: 14px" id = "maintable" class="table table-striped">
                        <thead>
                            <tr>
                               <th>Zone <a href = "sort?column=name&order=asc"> &uarr;</a><a href = "sort?column=name&order=desc">&darr; </a> </th>
							   <th>Total Area (km<sup>2</sup>) <a href = "sort?column=totalarea&order=asc"> &uarr;</a><a href = "sort?column=totalarea&order=desc">&darr; </a> </th>
							   <th>Population <a href = "sort?column=population&order=asc"> &uarr;</a><a href = "sort?column=population&order=desc">&darr; </a> </th>
							   <th>Dominant Tree Genus <a href = "sort?column=vegetation&order=asc"> &uarr;</a><a href = "sort?column=vegetation&order=desc">&darr; </a> </th>
							   <th>Provinces <a href = "sort?column=provinces&order=asc"> &uarr;</a><a href = "sort?column=provinces&order=desc">&darr; </a> </th>
							   <th>Options</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="ecozone" items="${listEcozone}">

                                <tr>
                                    <td>
                                        <c:out value="${ecozone.name}" />
                                    </td>
                                    <td>
                                        <c:out value="${ecozone.totalArea}" />
                                    </td>
                                    <td>
                                        <c:out value="${ecozone.population}" />
                                    </td>
                                    <td>
                                        <c:out value="${ecozone.vegetation}" />
                                    </td>
                                    <td>
                                        <c:out value="${ecozone.provinces}" />
                                    </td>
                                    <td><a href="edit?name=<c:out value='${ecozone.name}' />">Edit</a> <a href="delete?name=<c:out value='${ecozone.name}' />">Delete</a> <br>
                                     <a href="stats?name=<c:out value='${ecozone.name}' />">View stats</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>
                    </table>
             
                </div>
            </div>
        </body>

        </html>