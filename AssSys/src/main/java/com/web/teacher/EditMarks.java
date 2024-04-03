package com.web.teacher;

import com.pojo.Assessment;
import com.service.CourseServer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/edit")
public class EditMarks extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userid = Integer.parseInt(request.getParameter("userid"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        float marks = Float.parseFloat(request.getParameter("marks"));
        Assessment assessment = new Assessment(userid, courseId);

        //Get the type of marks;
        String type = request.getParameter("type");

        switch (type) {
            case "quiz":
                assessment.setQuiz(marks);
            case "assignment":
                assessment.setAssignment(marks);
            case "exam":
                assessment.setExam(marks);
        }

        // update value
        CourseServer courseServer = CourseServer.getInstanceOfCourseServer();
        if (courseServer.updateAssess(assessment, type)) {
            request.setAttribute("result", "success");
        } else {
            request.setAttribute("result", "failure");
        }
        request.getRequestDispatcher("teacher/EditMarks.jsp").forward(request, response);
    }
}
