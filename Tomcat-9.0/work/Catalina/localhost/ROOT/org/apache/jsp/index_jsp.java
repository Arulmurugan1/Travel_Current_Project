/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.53
 * Generated at: 2023-12-16 09:57:19 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import com.web.common.*;
import com.web.util.*;
import javax.servlet.http.*;
import javax.servlet.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(6);
    _jspx_dependants.put("/loader/loader.jsp", Long.valueOf(1697945480115L));
    _jspx_dependants.put("/dbconnection.jsp", Long.valueOf(1697985748064L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1691510873767L));
    _jspx_dependants.put("jar:file:/M:/Git/My%20Repository/Completed/Travel_Current_Project/Travel/ROOT/WEB-INF/lib/jstl-1.2.jar!/META-INF/sql.tld", Long.valueOf(1153365282000L));
    _jspx_dependants.put("jar:file:/M:/Git/My%20Repository/Completed/Travel_Current_Project/Travel/ROOT/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153365282000L));
    _jspx_dependants.put("/commonFiles.jsp", Long.valueOf(1702717776715L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("java.sql");
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("com.web.util");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("java.io");
    _jspx_imports_packages.add("com.web.common");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fsql_005fsetDataSource_0026_005fvar_005fuser_005furl_005fpassword_005fdriver_005fnobody;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fsql_005fsetDataSource_0026_005fvar_005fuser_005furl_005fpassword_005fdriver_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fsql_005fsetDataSource_0026_005fvar_005fuser_005furl_005fpassword_005fdriver_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head lang=\"en\">\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\r\n");
      out.write("<title>SK Travels</title>\r\n");
      out.write("\r\n");

	final String CACHE_VERSION = "?version=18" ; 
	session.setAttribute("CACHE_VERSION", CACHE_VERSION);

      out.write("\r\n");
      out.write("<link rel=\"icon\" type=\"image/x-icon\" href=\"");
      out.print(request.getContextPath() );
      out.write("/Images/favIcon/fav.png\">\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/styleSheet/bootstrap.css");
      out.print( CACHE_VERSION );
      out.write("\" type=\"text/css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/styleSheet/index.css");
      out.print( CACHE_VERSION );
      out.write("\" type=\"text/css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/styleSheet/Pagination.css");
      out.print( CACHE_VERSION );
      out.write("\" type=\"text/css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/styleSheet/searchBox.css");
      out.print( CACHE_VERSION );
      out.write("\" type=\"text/css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/styleSheet/header.css");
      out.print( CACHE_VERSION );
      out.write("\" type=\"text/css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/styleSheet/ajax-loader.css");
      out.print( CACHE_VERSION );
      out.write("\" type=\"text/css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/styleSheet/loader/loader.css");
      out.print(CACHE_VERSION );
      out.write("\" type=\"text/css\">\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css");
      out.print( CACHE_VERSION );
      out.write(" type=\"text/css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css");
      out.print( CACHE_VERSION );
      out.write("\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css");
      out.print( CACHE_VERSION );
      out.write("\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/scripts/ajax/libs/jquery/3.5.1/jquery.min.js");
      out.print( CACHE_VERSION );
      out.write("\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/scripts/ajax/libs/jquery/3.6.0/jquery.min.js");
      out.print( CACHE_VERSION );
      out.write("\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/scripts/ajax/libs/jquery/1.9.1/jquery.min.js");
      out.print( CACHE_VERSION );
      out.write("\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/scripts/ajax/libs/jqueryUi/1.10.2/jquery-ui.min.js");
      out.print( CACHE_VERSION );
      out.write("\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/scripts/Validator.js");
      out.print( CACHE_VERSION );
      out.write("\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/scripts/ajaxCall.js");
      out.print( CACHE_VERSION );
      out.write("\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/scripts/loader/loader.js");
      out.print(CACHE_VERSION );
      out.write("\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/scripts/common.js");
      out.print( CACHE_VERSION );
      out.write("\" type=\"text/javascript\"></script>\r\n");
      out.write("\r\n");
      out.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js\"></script>\r\n");
      out.write("<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"http://code.jquery.com/jquery-1.10.2.js\"></script>  \r\n");
      out.write("<link href=\"https://fonts.googleapis.com/css?family=Roboto:400,500\" rel=\"stylesheet\" />\r\n");
      out.write("\r\n");
      out.write("<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\r\n");
      out.write("<div id=\"load-content\">\r\n");
      out.write("		<span class=\"spinner\">\r\n");
      out.write("			<i class=\"fa fa-spinner fa-spin\"></i>\r\n");
      out.write("		</span>\r\n");
      out.write("		<span class=\"mt-3 text-white\" style=\"font-size: 30px;\">\r\n");
      out.write("			Loading\r\n");
      out.write("		</span>\r\n");
      out.write("</div>");
      out.write("\r\n");
      out.write("<body><div class=\"loader-ajax\" style=\"display: none\"></div></body>\r\n");
      out.write("\r\n");
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

boolean adminUser = CommonFactory.isNull(session.getAttribute("role")).equals("Admin") ;
Properties prop    = Dbmanager.getProperties(Constant.CONNECTION_PROPERTIES);

      out.write("\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("	var ADMIN_USER = ");
      out.print(adminUser);
      out.write(" ;\r\n");
      out.write("</script>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("	<input type=hidden name=msg id=msg value='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${msg}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("'>\r\n");
      out.write("	<input type=hidden name=function id=function value='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${action}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("'>\r\n");
      out.write("	<input type=hidden name=adminUser id=adminUser value= ");
      out.print( adminUser );
      out.write(" >\r\n");
      out.write("\r\n");
      out.write("	");
      //  sql:setDataSource
      org.apache.taglibs.standard.tag.rt.sql.SetDataSourceTag _jspx_th_sql_005fsetDataSource_005f0 = (org.apache.taglibs.standard.tag.rt.sql.SetDataSourceTag) _005fjspx_005ftagPool_005fsql_005fsetDataSource_0026_005fvar_005fuser_005furl_005fpassword_005fdriver_005fnobody.get(org.apache.taglibs.standard.tag.rt.sql.SetDataSourceTag.class);
      boolean _jspx_th_sql_005fsetDataSource_005f0_reused = false;
      try {
        _jspx_th_sql_005fsetDataSource_005f0.setPageContext(_jspx_page_context);
        _jspx_th_sql_005fsetDataSource_005f0.setParent(null);
        // /dbconnection.jsp(21,1) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
        _jspx_th_sql_005fsetDataSource_005f0.setVar("db");
        // /dbconnection.jsp(21,1) name = driver type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
        _jspx_th_sql_005fsetDataSource_005f0.setDriver(prop.getProperty(Constant.DRIVER) );
        // /dbconnection.jsp(21,1) name = url type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
        _jspx_th_sql_005fsetDataSource_005f0.setUrl(prop.getProperty(Constant.URL) );
        // /dbconnection.jsp(21,1) name = user type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
        _jspx_th_sql_005fsetDataSource_005f0.setUser(prop.getProperty(Constant.USER) );
        // /dbconnection.jsp(21,1) name = password type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
        _jspx_th_sql_005fsetDataSource_005f0.setPassword(prop.getProperty(Constant.PASSWORD) );
        int _jspx_eval_sql_005fsetDataSource_005f0 = _jspx_th_sql_005fsetDataSource_005f0.doStartTag();
        if (_jspx_th_sql_005fsetDataSource_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return;
        }
        _005fjspx_005ftagPool_005fsql_005fsetDataSource_0026_005fvar_005fuser_005furl_005fpassword_005fdriver_005fnobody.reuse(_jspx_th_sql_005fsetDataSource_005f0);
        _jspx_th_sql_005fsetDataSource_005f0_reused = true;
      } finally {
        org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_sql_005fsetDataSource_005f0, _jsp_getInstanceManager(), _jspx_th_sql_005fsetDataSource_005f0_reused);
      }
      out.write("\r\n");
      out.write("	\r\n");
      out.write("	<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\"\r\n");
      out.write("		integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\"\r\n");
      out.write("		crossorigin=\"anonymous\"></script>\r\n");
      out.write("	<script\r\n");
      out.write("		src=\"https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js\"\r\n");
      out.write("		integrity=\"sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q\"\r\n");
      out.write("		crossorigin=\"anonymous\"></script>\r\n");
      out.write("	<script\r\n");
      out.write("		src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js\"\r\n");
      out.write("		integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\"\r\n");
      out.write("		crossorigin=\"anonymous\"></script>	\r\n");
      out.write("		\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("	<div class=\"container\">\r\n");
      out.write("		<div class=\"content\">\r\n");
      out.write("			<div class=\"card-body\">\r\n");
      out.write("				<nav>\r\n");
      out.write("					<div class=\"nav nav-tabs justify-content-end\" id=\"nav-tab\"\r\n");
      out.write("						role=\"tablist\">\r\n");
      out.write("						<a class=\"nav-item nav-link active\" id=\"nav-login-tab\"\r\n");
      out.write("							data-toggle=\"tab\" href=\"#loginTab\" role=\"tab\"\r\n");
      out.write("							aria-controls=\"loginTab\" aria-selected=\"true\"> Login </a> <a\r\n");
      out.write("							class=\"nav-item nav-link\" id=\"nav-register-tab\" data-toggle=\"tab\"\r\n");
      out.write("							href=\"#registerTab\" role=\"tab\" aria-controls=\"registerTab\"\r\n");
      out.write("							aria-selected=\"false\"> Register </a>\r\n");
      out.write("					</div>\r\n");
      out.write("				</nav>\r\n");
      out.write("				<form name=\"form\" method=post id=center>\r\n");
      out.write("\r\n");
      out.write("					<div class=\"tab-content\" id=\"nav-tabContent\">\r\n");
      out.write("						<div class=\"tab-pane fade show active text-center\" id=\"loginTab\"\r\n");
      out.write("							role=\"tabpanel\" aria-labelledby=\"nav-login-tab\">\r\n");
      out.write("							<div class=\"row\">\r\n");
      out.write("								<div class=\"col-auto mb-3\">\r\n");
      out.write("									<label for=\"username\">Enter UserName </label> <input\r\n");
      out.write("										type=\"text\" class=\"form-control \" name=\"txtUser\" id=txtUser\r\n");
      out.write("										maxlength=\"10\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${txtuser}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\" size=\"25\" autocomplete=\"off\" spellcheck=\"false\" >\r\n");
      out.write("								</div>\r\n");
      out.write("							</div>\r\n");
      out.write("							<div class=\"row\">\r\n");
      out.write("								<div class=\"col-auto mb-3 \">\r\n");
      out.write("									<label for=\"password\">Enter Password </label>\r\n");
      out.write("									<div class=input-group>\r\n");
      out.write("										<input type=\"password\" class=\"form-control \"\r\n");
      out.write("											name=\"txtPassword\" id=txtPassword maxlength=\"15\" value=\"\" autocomplete=\"off\">\r\n");
      out.write("										<div class=input-group-append>\r\n");
      out.write("											<div class=input-group-text>\r\n");
      out.write("												<i class=\"fa fa-eye-slash check-icon\" data-target=txtPassword aria-hidden=\"true\" ></i>\r\n");
      out.write("											</div>\r\n");
      out.write("										</div>\r\n");
      out.write("									</div>\r\n");
      out.write("								</div>\r\n");
      out.write("							</div>\r\n");
      out.write("\r\n");
      out.write("							<div class=\"row\">\r\n");
      out.write("								<div class=\"col text-center\">\r\n");
      out.write("									<button type=\"submit\" onclick=\"Submit('login')\"\r\n");
      out.write("										class=\"btn btn-success button-length btn-block w-100 mt-2\">\r\n");
      out.write("										<i class=\"fa fa-sign-in mr-2\"></i>Login\r\n");
      out.write("									</button>\r\n");
      out.write("									<button type=\"reset\"\r\n");
      out.write("										class=\"btn btn-success button-length btn-block w-100 mt-4\">\r\n");
      out.write("										<i class=\"fa fa-close mr-2\"></i>Cancel\r\n");
      out.write("									</button>\r\n");
      out.write("								</div>\r\n");
      out.write("							</div>\r\n");
      out.write("\r\n");
      out.write("						</div>\r\n");
      out.write("						<div class=\"tab-pane fade text-center p-3 m-3\" id=\"registerTab\"\r\n");
      out.write("							role=\"tabpanel\" aria-labelledby=\"nav-register-tab\">\r\n");
      out.write("							<h4 class=text-center>Add New User</h4>\r\n");
      out.write("\r\n");
      out.write("							<div class=row>\r\n");
      out.write("								<div class=\"col-auto\">\r\n");
      out.write("									<label>Enter UserName</label> <input type=\"text\"\r\n");
      out.write("										class=\"form-control\" name=\"username\" id=username>\r\n");
      out.write("								</div>\r\n");
      out.write("								<div class=\"col-auto\">\r\n");
      out.write("									<label>Enter Password</label>\r\n");
      out.write("									<div class=input-group>\r\n");
      out.write("										<input type=\"password\" class=\"form-control\" name=\"pass1\" id=pass1 size=\"16\">\r\n");
      out.write("										<div class=input-group-append>\r\n");
      out.write("											<div class=input-group-text>\r\n");
      out.write("												<i class=\"fa fa-eye-slash check-icon\" data-target=pass1 aria-hidden=\"true\"></i>\r\n");
      out.write("											</div>\r\n");
      out.write("										</div>\r\n");
      out.write("									</div>\r\n");
      out.write("								</div>\r\n");
      out.write("							</div>\r\n");
      out.write("							<div class=\"row p-lg-3\">\r\n");
      out.write("\r\n");
      out.write("								<div class=\"col-lg-\">\r\n");
      out.write("									<label>Re-Enter Password</label>\r\n");
      out.write("									<div class=input-group>\r\n");
      out.write("										<input type=\"password\" class=\"form-control\" name=\"pass2\" id=pass2 size=\"16\">\r\n");
      out.write("										<div class=input-group-append>\r\n");
      out.write("											<div class=input-group-text>\r\n");
      out.write("											<i class=\"fa fa-eye-slash check-icon\" data-target=pass2 aria-hidden=\"true\"></i>\r\n");
      out.write("											</div>\r\n");
      out.write("										</div>\r\n");
      out.write("									</div>\r\n");
      out.write("								</div>\r\n");
      out.write("								<div class=\"col-auto\">\r\n");
      out.write("									<label>Enter Login Id</label> <input type=\"text\"\r\n");
      out.write("										class=\"form-control\" name=\"user_id\" id=user_id>\r\n");
      out.write("								</div>\r\n");
      out.write("							</div>\r\n");
      out.write("\r\n");
      out.write("							<div class=\"d-flex justify-content-center  mt-3\">\r\n");
      out.write("								<div class=\"mr-4\">\r\n");
      out.write("									<button type=\"button\" class=\"btn btn-success button-length\"\r\n");
      out.write("										onclick=\"Submit('register');\">Add</button>\r\n");
      out.write("								</div>\r\n");
      out.write("								<div>\r\n");
      out.write("									<input type=\"reset\" class=\"btn btn-success button-length mr-4\"\r\n");
      out.write("										value=Reset>\r\n");
      out.write("								</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("							</div>\r\n");
      out.write("\r\n");
      out.write("						</div>\r\n");
      out.write("					</div>\r\n");
      out.write("				</form>\r\n");
      out.write("			</div>\r\n");
      out.write("		</div>\r\n");
      out.write("	</div>\r\n");
      out.write("\r\n");
      out.write("	<script src=\"");
      out.print(request.getContextPath() );
      out.write("/scripts/index.js");
      out.print( CACHE_VERSION );
      out.write("\"></script>\r\n");
      out.write("	<script src=\"https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.1.1/crypto-js.min.js\" integrity=\"sha512-E8QSvWZ0eCLGk4km3hxSsNmGWbLtSCSUcewDQPQWZF6pEU8GlT8a5fF32wOl1i8ftdMhssTrF/OhyGWwonTcXA==\" crossorigin=\"anonymous\" referrerpolicy=\"no-referrer\"></script>\r\n");
      out.write("</body>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
