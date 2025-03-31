<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
        <html>

        <head>
            <title>Canadian Terrestrial Ecozone Database</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        </head>

        <body style="font-family: monospace ;background-color:beige">

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: darkgreen">
                    <div>
                        <a href=/simple/list class="navbar-brand" style="color: white"> Canadian Terrestrial Ecozone Database </a> <a style="margin-left: 55em" class="navbar-brand"  href="about">About</a>
                    </div>
                </nav>
            </header>
            <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body"  style="background-color: beige; border: 1px solid black">
                        <c:if test="${ecozone != null}">
                            <form action="update" method="post">
                        </c:if>
                        <c:if test="${ecozone == null}">
                            <form action="insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${ecozone != null}">
                                    ${ecozone.name}
                                </c:if>
                                <c:if test="${ecozone == null}">
                                    New Ecozone
                                </c:if>
                            </h2>
                        </caption>
						<form>
                        <c:if test="${ecozone == null}">
                            <fieldset class="form-group">
                            <label>Zone:</label> <input type="text" value="<c:out value='${ecozone.name}' />" class="form-control" name="name" maxlength="50" required="required">
                        </fieldset>
                        </c:if>
                        <c:if test="${ecozone != null}">
                                  <fieldset class="form-group">
                            <input type="hidden" value="<c:out value='${ecozone.name}' />" class="form-control" name="name" required="required" maxlength="50">
                        </fieldset>
                        </c:if>
                        

                        <fieldset class="form-group">
                            <label>Total Area (km<sup>2</sup>):</label> <input type="number" value="<c:out value='${ecozone.totalArea}' />" class="form-control" name="totalArea" required="required" min="0" max ="9985000">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Population:</label> <input type="number" value="<c:out value='${ecozone.population}' />" class="form-control" name="population" required="required" min="0" max ="1000000000">
                        </fieldset>
                        
<!--                         <fieldset class="form-group"> -->
<%--                             <label>Dominant Tree Genus</label> <input type="text" value="<c:out value='${ecozone.vegetation}' />" class="form-control" name="vegetation"> --%>
<!--                         </fieldset> -->
                        
                        <fieldset class="form-group">
                        
                            <label for="vegetation">Dominant Tree Genus: </label> 
                            
                                                        
                            <select name="vegetation" required="required" class = "form-control" >
                            <option selected ="selected"> ${ecozone.vegetation} </option>
                            <option value = "Spruce" ${ecozone.vegetation == Spruce ? "selected" : ""}>Spruce</option>
                            <option value = "Pine" ${ecozone.vegetation == Pine ? "selected" : ""}>Pine</option>
                            <option value = "Fir" ${ecozone.vegetation == Fir ? "selected" : ""}>Fir</option>
                            <option value = "Larch" ${ecozone.vegetation == Larch ? "selected" : ""}>Larch</option>
                            <option value = "Poplar" ${ecozone.vegetation == Poplar ? "selected" : ""}>Poplar</option>
                            <option value = "Maple" ${ecozone.vegetation == Maple ? "selected" : ""}>Maple</option>
                            <option value = "Hemlock" ${ecozone.vegetation == Hemlock ? "selected" : ""}>Hemlock</option>
                            <option value = "Birch" ${ecozone.vegetation == Birch ? "selected" : ""}>Birch</option>
                            <option value = "Cedar" ${ecozone.vegetation == Cedar ? "selected" : ""}>Cedar</option>
                            <option value = "Other Hardwood" ${ecozone.vegetation == OtherHardwood ? "selected" : ""}>Other Hardwood</option>
                            <option value = "Other Conifer" ${ecozone.vegetation == OtherConifer ? "selected" : ""}>Other Conifer</option>
                            <option value = "None" ${ecozone.vegetation == None ? "selected" : ""}>None</option>
                            
                            </select>
                        </fieldset>
                        
                        

                        <fieldset class="form-group">
                            <label>Provinces:</label> <input type="text" value="<c:out value='${ecozone.provinces}' />" class="form-control" name="provinces" maxlength="50" required="required">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>