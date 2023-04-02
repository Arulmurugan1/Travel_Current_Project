package com.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.common.Constant;
import com.web.common.LoggerFactory;
import com.web.modal.Driverdao;
import com.web.objects.Driver;

@WebServlet("/Driver")
public class DriverServlet extends CustomServlet {
    private static final long serialVersionUID = 1L;

    public DriverServlet() {
        super();

    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Driver v = new Driver();
        Driverdao dao = new Driverdao();
        List<Driver> l = new ArrayList<Driver>();
        String message = "";

        try {
            
            super.service(request,this, response);

            if (mode != null && !mode.trim().equals("N") && !mode.trim().equals(""))
            {
                String name = request.getParameter("driver_name");
                String gender = request.getParameter("gender");
                String city = request.getParameter("city");
                String phone = request.getParameter("phone");
                String no = request.getParameter("vehicle_no");
                int age = Integer.parseInt(request.getParameter("age"));

                if (mode.equals("E")) {
                    int id = Integer.parseInt(request.getParameter("driver_id"));
                    v = dao.selectDriver(id);
                    request.setAttribute("listUser", v);
                }
                if (mode.equals("I")) {
                    v = new Driver(name, gender, city, phone, no, age, 0);
                    if (dao.insertDriver(v)) {
                        message = "success";
                    } else {
                        message = "Failed";
                    }
                }

                if (mode.equals("U")) {
                    int id = Integer.parseInt(request.getParameter("driver_id"));
                    v = new Driver(name, gender, city, phone, no, age, id);

                    if (dao.updateDriver(v)) {
                        message = "success";
                    } else {
                        message = "Failed";
                    }
                }

                if (mode.equals("D")) {
                    no = request.getParameter("vehicle_no");

                    if (dao.deleteDriver(no)) {
                        message = "success";
                    } else {
                        message = "Failed";
                    }

                }
            }
                l = dao.getAllDriver();
                dao.closeAll();
        } catch (Exception e) {
            logContent(e.toString(), LoggerFactory.ERROR, e);
            message = e.getMessage();
        } finally 
        {
            request.setAttribute("msg", message);
            request.setAttribute("listUser", l);
            
            if (message == "success" || mode.equals("")) {
                request.getRequestDispatcher(Constant.DRIVER_JSP).forward(request, response);

            } else {
                request.getRequestDispatcher(Constant.DRIVER_INSERT_JSP).forward(request, response);
            }
        }
    }
}
