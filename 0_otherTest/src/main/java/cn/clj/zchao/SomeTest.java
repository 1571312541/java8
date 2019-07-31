package cn.clj.zchao;

import org.apache.commons.lang3.StringUtils;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * 〈〉
 *
 * @author zc
 * @create 2018/12/7
 */
public class SomeTest {

    @Test
    public void te4(){


    }
    @Test
    public void te2(){
        ArrayList<Object> list = new ArrayList<>();
        ArrayList<Object> list2 = new ArrayList<>();

        boolean b = list.addAll(list2);
        System.out.println(list);

    }
    @Test
    public void te3(){

        System.out.println(StringUtils.isNotBlank(""));

    }
    @Test
    public void getLeastMaximumTest(){
        Integer month = 4;
        Calendar cal = Calendar.getInstance();
        // 设置月份
        cal.set(Calendar.MONTH, month - 1);
        // 获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        // 设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfMonth = sdf.format(cal.getTime())+" 00:00:00";
        System.out.println(firstDayOfMonth);

        Calendar cal2 = Calendar.getInstance();
        // 设置月份
        cal.set(Calendar.MONTH, month - 1);
        // 获取某月最大天数
        int lastDay=0;
        //2月的平年瑞年天数
        if(month==2) {
            lastDay = cal.getLeastMaximum(Calendar.DAY_OF_MONTH);
        }else {
            lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        // 设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        // 格式化日期
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime())+" 23:59:59";

        System.out.println(lastDayOfMonth);


    }
    @Test
    public void t(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String nowTime = format.format(date);
        System.out.println(nowTime);
        try {
            Date parse = format.parse(nowTime);
            System.out.println(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void test3(){

        String s = "132";
        String[] userPhoneSplit = s.split(",");
        System.out.println(userPhoneSplit.length);
        System.out.println(userPhoneSplit[0]);
    }
    @Test
    public void test8() {
        Long l = 0L;
        Long l2 = 1111111L;
        System.out.println(l.longValue() == 0);


    }
    @Test
    public void test10() {
        User u = new User();
        Integer a = null;
        u.setName("sss");
        u.setAge(a);
        System.out.println(u);
    }

    @Test
    public void test4(){  //


        long ms = 92861931; //1天1时47分41秒931毫秒
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;

        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        System.out.println(day + "天" + hour + "时" + minute + "分" + second + "秒" + milliSecond + "毫秒");

    }
    @Test
    public void test11(){

        String formate = "25,47,1";
        String[] split = formate.split(",");
        String hour = split[0];
        String minute = split[1];
        String second = split[2];
        String ss = null;
        if (second != null) {
            if (Double.parseDouble(second) > 0) {
                Integer v = Integer.parseInt(minute) + 1;
                minute =  v.toString();
            }
        }
        if (hour != null && !"0".equals(hour)) {
            ss = hour + "小时" + minute + "分";
        }else {
            //不足一分钟计一分钟
            if ("0".equals(minute) && !"0".equals(second)) {
                minute = "1";
            }

            ss = minute + "分";
        }
        System.out.println(ss);


    }
    @Test
    public void test13() {
        Integer s = 16/60;
        System.out.println(s);


    }
    @Test
    public void test14() {
//        Integer s = 16/60;
        Long time = 92861931L;
        double dbtime = time.doubleValue();
        Double ceil = Math.ceil(dbtime / 60 / 1000);

        System.out.println(ceil.intValue());


    }
    @Test
    public void test15() {
//        Integer s = 16/60;
        Integer time = 547;
        if (time == null || time == 0) {
            System.out.println("-小时-分");
        }
        int hour = time / 60;
        int minute = time - hour * 60;

        if (hour > 0) {
            System.out.println(hour + "小时" + minute + "分");
        }else {
            System.out.println(minute + "分");
        }




    }
    @Test
    public void test91() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = format.format(new Date());
        System.out.println(format1);
    }
    @Test
    public void test9(){

        long ms = 29185574; //1天1时47分41秒931毫秒
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;

        long hour = (ms) / hh; //25
        long minute = (ms - hour *hh) / mi; //47
        long second = (ms - hour * hh - minute * mi) / ss;

        System.out.println(hour + "时" + minute + "分" + second + "秒");

    }


    @Test
    public void test5(){

        //生成的设备编码E开头
        String equipCode = "E";
        //加上 设备类型的首字母（大写）
        String headChar = Pinyin.getPinYinHeadChar("顶顶顶顶顶顶顶顶顶");
        if (headChar.length() > 6) {
            headChar = headChar.substring(0, 6);
        }
        equipCode += headChar.toUpperCase();
        //加上 年月
        SimpleDateFormat fmt = new SimpleDateFormat( "yyyyMM");

        String date =  fmt.format(new Date());
        date = date.substring(2, 6);
        equipCode += date;
        //获取随机数长度
        int length = 16 - equipCode.length();
        //加上随机数
        int randomVar = (int)((Math.random()*9+1)*Math.pow(10, length-1));

        //判断是否重复
        equipCode += randomVar;
        System.out.println(length);
        System.out.println(equipCode);
        System.out.println(equipCode.length());

    }
    @Test
    public void test7(){

        double distance = getDistance(29.490295, 106.486654,
                29.615467, 106.581515);
        System.out.println("距离" + distance / 1000 + "公里"); //16689.6


    }

    @Test
    public void test6(){
        GlobalCoordinates source = new GlobalCoordinates(29.490295, 106.486654);
        GlobalCoordinates target = new GlobalCoordinates(29.615467, 106.581515);

        double meter1 = getDistanceMeter(source, target, Ellipsoid.Sphere);
        double meter2 = getDistanceMeter(source, target, Ellipsoid.WGS84);
        double meter3 = getDistanceMeter(source, target, Ellipsoid.Clarke1880);
        double meter4 = getDistanceMeter(source, target, Ellipsoid.ANS);
        double meter5 = getDistanceMeter(source, target, Ellipsoid.Clarke1858);
        double meter6= getDistanceMeter(source, target, Ellipsoid.GRS80);
        double meter7 = getDistanceMeter(source, target, Ellipsoid.GRS67);
        double meter8= getDistanceMeter(source, target, Ellipsoid.WGS72);

        System.out.println("Sphere坐标系计算结果："+meter1 + "米");
        System.out.println("WGS84坐标系计算结果："+meter2 + "米");
        System.out.println("Clarke1880坐标系计算结果："+meter3 + "米");
        System.out.println("ANS坐标系计算结果："+meter4 + "米");
        System.out.println("Clarke1858坐标系计算结果："+meter5 + "米");
        System.out.println("GRS80坐标系计算结果："+meter6 + "米");
        System.out.println("GRS67坐标系计算结果："+meter7 + "米");
        System.out.println("WGS72坐标系计算结果："+meter8 + "米");


    }

    @Test
    public void GetDistance() {  //16.69
        double sd = 0D;
        long t = System.currentTimeMillis();
        for (int i = 0; i <1000; i++) {
            double lat1 = 29.490295+i;
            double lng1 = 106.486654+i;
            double lat2 = 29.615467;
            double lng2 = 106.581515;
            double radLat1 = rad(lat1);
            double radLat2 = rad(lat2);
            double a = radLat1 - radLat2;
            double b = rad(lng1) - rad(lng2);
            double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                    Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
            s = s * EARTH_RADIUS;
            s = new BigDecimal(s).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out.println(s);

            sd += s;
        }
        System.out.println(sd);

        System.out.println(System.currentTimeMillis()-t);

    }


    public static double getDistanceMeter(GlobalCoordinates gpsFrom, GlobalCoordinates gpsTo, Ellipsoid ellipsoid)
    {
        //创建GeodeticCalculator，调用计算方法，传入坐标系、经纬度用于计算距离
        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, gpsFrom, gpsTo);

        return geoCurve.getEllipsoidalDistance();
    }


    private static double EARTH_RADIUS = 6378.137;
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public static double getDistance(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s * 1000;
        return s;
    }


    @Test
    public void t11() {
        Date date = new Date();

        Date startTime = DateUtils.formatDate2Date(date,"yyyy-MM-dd 00:00:00");
        Date endTime =  DateUtils.formatDate2Date(date,"yyyy-MM-dd HH:mm:ss");
        System.out.println(startTime);
        System.out.println(endTime);
    }

    @Test
    public void t12() {
        Date date = new Date();


        System.out.println(date.getTime()>2*60*1000);
        Long time = date.getTime();
        Integer s = 2*60*1000;
        System.out.println(time > s);
        System.out.println(time.longValue() >s.intValue());
    }

    @Test
    public void t13() {
        Date date = new Date();
        // abcd

        System.out.println(date.getTime()>2*60*1000);
        Long time = date.getTime();
        Integer s = 2*60*1000;
        System.out.println(time > s);
        System.out.println(time.longValue() >s.intValue());
    }

    @Test
    public void t18() {
        String tonnage="";
        if ( tonnage == null || tonnage.length() == 0|| tonnage.trim().length() < 1 || " ".equals(tonnage)) {
            System.out.println(1);
        }else {
            System.out.println(2);
        }
    }
    @Test
    public void t17() {
        if (new Date(0L).getTime() == 0) {
            System.out.println(1);

        }else {
            System.out.println(2);

        }
    }
    @Test
    public void t19() {
        Date d = new Date(1562845145000L);
        long time = d.getTime();
        System.out.println(time);
        String endTime =  DateUtils.formatDate2String(d,"yyyy-MM-dd HH:mm:ss");
        System.out.println(endTime);
    }
    @Test
    public void t16() {
        int a = 0;
//        int a = 17;
        int n = (int) (a/0.75 - 1);
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        System.out.println(n + 1);
    }
    @Test
    public void t15() {
        System.out.println(new Date(0L));
    }
@Test
    public void t20() {
        System.out.println(Math.round(11.5));
        System.out.println(Math.round(-11.5));
    }

    /**
     * 球从100m高度落下，每次落地后反弹回原来高度的一半，在落下，求第十次落地时，共多少m，第10次反弹多高
     */
    @Test
    public void t21() {
        double initialMile = 100d;
        double nextMile = 100d;
        double totalMile = 100d;
        for (int i= 1;i<10; i++){
            totalMile = totalMile +nextMile;
            nextMile = nextMile / 2;
        }
        System.out.println("路程："+totalMile);
        System.out.println("高度："+nextMile / 2);

        System.out.println(totalMile);
        System.out.println(nextMile);
    }
    @Test
    public void t22() {
        float initialMile = 100f;
        System.out.println(getTotalMile(initialMile, 10));
        System.out.println(getNextMile(initialMile, 10));

    }

    public float getTotalMile(Float initialMile, Integer n) {
        //100  100+50+50=200  100+50+50+25+25=250 100+50+50+25+25+12.5+12.5=275  275+6.25+6.25 = 287.5
        float totalMile = initialMile;
        for (int i = 1; i <= n; i++) {
            if (n == 1) {
                return initialMile;
            }else {
                initialMile = initialMile/2;
                totalMile += initialMile*2;
            }
        }
        return totalMile;
    }
    public float getNextMile(Float initialMile, Integer n) {
        //100  100+50+50=200  100+50+50+25+25=250 100+50+50+25+25+12.5+12.5=275  275+6.25+6.25 = 287.5
        float nextMile = 0f;

        for (int i = 1; i <= n; i++) {
            nextMile = initialMile/2;
            initialMile = nextMile;
        }
        return nextMile;
    }

}
