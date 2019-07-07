package cn.hkxj.platform.pojo.wechat;

import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.timetable.ExamTimeTable;
import org.joda.time.DateTime;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author FMC
 * @date 2019/6/24 14:10
 */
public class ExamGroupMsg {

    private Classes classes;

    private List<ExamTimeTable> examTimeTables;

    private List<ScheduleTask> scheduleTasks;

    public ExamGroupMsg() {
    }

    public String getCourseContent() {
        StringBuilder builder = new StringBuilder();
        if (CollectionUtils.isEmpty(examTimeTables))
            builder.append("还没有您的考试时间\n");
        else {
            builder.append("同学你好，你明天有考试安排如下：\n");
            for (ExamTimeTable examTimeTable : examTimeTables) {
                if (!Objects.equals(examTimeTable, null)) {
                    DateTime start = new DateTime(examTimeTable.getStart());
                    DateTime end = new DateTime(examTimeTable.getEnd());
                    builder.append("考试科目：").append(examTimeTable.getCourse().getName()).append("\n")
                            .append("考试时间：").append(start.getYear()).append("年").append(start.getMonthOfYear()).append("月")
                            .append(start.getDayOfMonth()).append("日 ")
                            .append(dateTimeHour(start)).append(dateTimeToText(start)).append(" - ").append(dateTimeToText(end)).append("\n")
                            .append("考试地点：").append(examTimeTable.getRoom().getName()).append("\n");
                }
            }
            builder.append("大家一起过过过！！\n信息仅供参考，以学校通知为准。");
        }
        return builder.toString();
    }
    private String dateTimeToText(DateTime dateTime) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(dateTime.getHourOfDay()).append(":").append(dateTime.getMinuteOfHour());
        return new String(stringBuffer);
    }

    private String dateTimeHour(DateTime dateTime) {
        if (dateTime.getHourOfDay() < 12)
            return "上午";
        else
            return "下午";
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public List<ExamTimeTable> getExamTimeTables() {
        return examTimeTables;
    }

    public void setExamTimeTables(List<ExamTimeTable> examTimeTables) {
        this.examTimeTables = examTimeTables;
    }

    public List<ScheduleTask> getScheduleTasks() {
        return scheduleTasks;
    }

    public void setScheduleTasks(List<ScheduleTask> scheduleTasks) {
        this.scheduleTasks = scheduleTasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamGroupMsg that = (ExamGroupMsg) o;
        return Objects.equals(classes, that.classes) &&
                Objects.equals(examTimeTables, that.examTimeTables) &&
                Objects.equals(scheduleTasks, that.scheduleTasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classes, examTimeTables, scheduleTasks);
    }

    @Override
    public String toString() {
        return "ExamGroupMsg{" +
                "classes=" + classes +
                ", examTimeTables=" + examTimeTables +
                ", scheduleTasks=" + scheduleTasks +
                '}';
    }
}
