<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="col-md-8">
    <div class="tbox tag-box-v3 margin-bottom-40">
        <div class="heading heading-v2 margin-bottom-40">
            <h2>Contact Information</h2>
        </div>
        <div class="contact">
			<div class="row">
                <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 margin-bottom-40">
                    <p>
                        Legislative and Parliamentary Affairs Division<br/>
                        Ministry of Law, Justice and Parliamentary Affairs<br/>
                        Bangladesh Secretariat, Dhaka - 1000, Bangladesh.<br/>
                    </p>           
                </div>
                <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 margin-bottom-40">
                    <p>
                        Phone : +88-02-9545011<br/>
                        E-mail : <a href="mailto:ap@legislativediv.gov.bd" target="_top">ap@legislativediv.gov.bd</a><br/>
                    </p>           
                </div>         
            </div>
        </div>
        <div class="heading heading-v2 margin-bottom-40">
            <h2>Feedback Form</h2>
        </div>
        <div class="notice">
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 margin-bottom-40">
                    <form class="form-horizontal" action="" method="post">
                        <fieldset>
                            <!-- Name input-->
                            <div class="form-group">
                                <label class="col-md-2 control-label" for="name">Name</label>
                                <div class="col-md-8">
                                    <input id="name" name="name" type="text" placeholder="Your name" class="form-control">
                                </div>
                            </div>

                            <!-- Address -->
                            <div class="form-group">
                              <label class="col-md-2 control-label" for="message">Address</label>
                              <div class="col-md-8">
                                <textarea class="form-control" id="address" name="address" placeholder="Please enter your address here..." rows="5"></textarea>
                              </div>
                            </div>

                            <!-- Country-->
                            <div class="form-group">
                                <label class="col-md-2 control-label" for="name">Country</label>
                                <div class="col-md-8">
                                    <input id="country" name="country" type="text" placeholder="Country" class="form-control">
                                </div>
                            </div>

                            <!--Phone / Mobile-->
                            <div class="form-group">
                                <label class="col-md-2 control-label" for="name">Phone / Mobile</label>
                                <div class="col-md-8">
                                    <input id="phone" name="phone" type="text" placeholder="Phone or Mobile" class="form-control">
                                </div>
                            </div>

                            <!--Fax-->
                            <div class="form-group">
                                <label class="col-md-2 control-label" for="name">Fax</label>
                                <div class="col-md-8">
                                    <input id="fax" name="fax" type="text" placeholder="Fax" class="form-control">
                                </div>
                            </div>
    
                            <!-- Email input-->
                            <div class="form-group">
                              <label class="col-md-2 control-label" for="email">Your E-mail</label>
                              <div class="col-md-8">
                                <input id="email" name="email" type="text" placeholder="Your email" class="form-control">
                              </div>
                            </div>

                            <!--Subject-->
                            <div class="form-group">
                                <label class="col-md-2 control-label" for="name">Subject</label>
                                <div class="col-md-8">
                                    <input id="subject" name="subject" type="text" placeholder="Subject" class="form-control">
                                </div>
                            </div>
    
                            <!-- Message body -->
                            <div class="form-group">
                              <label class="col-md-2 control-label" for="message">Your message</label>
                              <div class="col-md-8">
                                <textarea class="form-control" id="message" name="message" placeholder="Please enter your message here..." rows="5"></textarea>
                              </div>
                            </div>
    
                            <!-- Form actions -->
                            <div class="form-group">
                              <div class="col-md-6 text-right">
                                <button type="submit" class="btn btn-primary btn-lg">Submit</button>
                              </div>
                              <div class="col-md-6 text-left">
                                <button type="submit" class="btn btn-primary btn-lg">Reset</button>
                              </div>
                            </div>
                        </fieldset>
                    </form>      
                </div>
            </div>
        </div>
    </div>
</div>