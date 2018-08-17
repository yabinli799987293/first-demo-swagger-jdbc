package com.example.firstdemoswaggerjdbc.repostory;

import com.example.firstdemoswaggerjdbc.JDBCUtil;
import com.example.firstdemoswaggerjdbc.table.Zhangwu;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
public class ZwRepostory {
    //账务列表
    private static List<Zhangwu> list;
    //账务列表中的zwid最大值
    private static int maxIndex;

    public static List<Zhangwu> getList() {
        return list;
    }

    public int getMaxIndex(){
        list=allData2();
        maxIndex=list.get(list.size()-1).getZwid();
        return maxIndex;
    }

    /**
     * 添加账务
     * @param zhangwu
     * @return
     */
    //返回全部账务列表
    public List<Zhangwu> add(Zhangwu zhangwu){
        list=allData2();
        Connection conn=JDBCUtil.getConn();
        String sql="insert into zhangwu(flname,money,zhanghu,createtime,description) values(?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt=conn.prepareStatement(sql);
            //pstmt.setInt(1, zhangwu.getZwid());
            pstmt.setString(1, zhangwu.getFlname());
            pstmt.setDouble(2, zhangwu.getMoney());
            pstmt.setString(3, zhangwu.getZhanghu());
            pstmt.setString(4, zhangwu.getCreatetime());
            pstmt.setString(5, zhangwu.getDescription());

            //执行
            pstmt.execute();    //执行返回boolean值
            pstmt.close();
            conn.close();

            list.add(zhangwu);
            maxIndex=getMaxIndex();
            //System.out.println(maxIndex);
            list.get(list.size()-1).setZwid(maxIndex);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //返回添加的一个账务
    public Zhangwu add2(Zhangwu zhangwu){
        list=allData2();
        Connection conn=JDBCUtil.getConn();
        String sql="insert into zhangwu(flname,money,zhanghu,createtime,description) values(?,?,?,?,?)";
        PreparedStatement pstmt;
        Zhangwu list_zhangwu=null;
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, zhangwu.getFlname());
            pstmt.setDouble(2, zhangwu.getMoney());
            pstmt.setString(3, zhangwu.getZhanghu());
            pstmt.setString(4, zhangwu.getCreatetime());
            pstmt.setString(5, zhangwu.getDescription());

            //执行
            pstmt.execute();    //返回受影响的列
            pstmt.close();
            conn.close();

            list.add(zhangwu);
            maxIndex=getMaxIndex();
            //System.out.println(maxIndex);
            list_zhangwu=list.get(list.size()-1);
            list_zhangwu.setZwid(maxIndex);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_zhangwu;
    }


    /**
     * 编辑账务
     * @param zhangwu
     */
    //暂时不用，直接修改所有的更简单
    public String edit(Zhangwu zhangwu){
        Zhangwu temp=zhangwu;

        if (!zhangwu.equals(temp)){
            return "编辑成功";
        }else {
            return "编辑了重复内容或者编辑失败";
        }
    }

    //编辑账务用这个
    //id为主键，先找到相应的记录，然后根据后边的属性编辑
    public String edit(int zwid,String flname,double money,String zhanghu,String createtime,String description){

        Zhangwu zhangwuOld=null;
        Zhangwu zhangwuNew=null;

        list=allData2();
        for (Zhangwu temp:list){        //寻找zwid的账务
            if (temp.getZwid()==zwid){
                zhangwuOld=temp;
            }
        }

        Connection conn=JDBCUtil.getConn();
        String sql="update zhangwu set flname='"+flname+"',"
                +"money='"+money+"',"
                +"zhanghu='" +zhanghu+"',"
                +"createtime='" +createtime+"',"+
                "description='"+description+"'"+"where zwid='"+zwid+"'";
        PreparedStatement pstmt=null;
        try {
            pstmt=conn.prepareStatement(sql);
            int result=pstmt.executeUpdate();
            pstmt.close();
            conn.close();

            //空指针异常，已解决
            if (result>0){
                //中间变量
                Zhangwu zhangwuTemp=new Zhangwu(zwid,flname,money,zhanghu,createtime,description);
                zhangwuNew=zhangwuTemp;
                return zhangwuOld.toString()+"\n改变为：\n"+zhangwuNew.toString();
            }else{
                return "编辑失败";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "编辑失败";
        }
    }

    /**
     * 删除账务
     * @param
     */
    //错误，不采用
    public String delete(int zwid){
        Connection conn=JDBCUtil.getConn();
        String sql="DELETE FROM zhangwu where id=?";
        int i;
        PreparedStatement pstmt;
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, zwid);
            boolean result=pstmt.execute();

            pstmt.close();
            conn.close();
            if (result){
                Zhangwu zhangwu=searchOfZwid(zwid);
                list.remove(zhangwu);
                if(!list.contains(zhangwu)){
                    return zhangwu.toString()+"\n删除成功";
                }else {
                    return "删除事务失败";
                }
            }else{
                return "删除事务失败";
            }

        } catch (SQLException e) {
            e.printStackTrace();
           return "删除事务失败";
        }

    }

    //正确
    public String delete2(int zwid){
        Connection conn=JDBCUtil.getConn();
        String sql="delete from zhangwu where zwid='"+zwid+"'";
        PreparedStatement pstmt;
        int i=0;
        try {
            pstmt=conn.prepareStatement(sql);
            Zhangwu zwSearchForZwid=searchOfZwid(zwid);
            i=pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            if (i>0){
                return zwSearchForZwid.toString()+"\n删除成功";
            }
            return "删除失败";
        } catch (SQLException e) {
            e.printStackTrace();
            return "删除失败";
        }

    }

    /**
     * 查询账务
     * @param flname
     * @return
     */
    public List<Zhangwu> searchOfName(String flname){
        List<Zhangwu> sameNameList=new ArrayList<>();
        list=allData2();
        for (Zhangwu temp:list){
            if (temp.getFlname().equals(flname)){
                sameNameList.add(temp);
            }
        }
        return sameNameList;
    }

    public Zhangwu searchOfZwid(int zwid){
        Zhangwu zhangwu=null;
        list=allData2();
        for (Zhangwu temp:list){
            if (temp.getZwid()==zwid){
                zhangwu=temp;
                return zhangwu;
            }
        }
        return null;

    }

    public List<Zhangwu> searchOfTime(String createtime){
        List<Zhangwu> list_time=new ArrayList<>();
        list=allData2();
        for (Zhangwu temp:list){
            if (temp.getCreatetime().equals(createtime)){
                list_time.add(temp);
            }
        }
        return list_time;

    }


    public List<Zhangwu> searchAll(){
        list=allData2();
        return list;
    }

    /**
     * 获得全部的账务
     */
    //全局list，多个窗口点击会出现重复添加数据
    public void allData(){
        //List<Zhangwu> list_temp=new ArrayList<>();
        Connection conn=JDBCUtil.getConn();
        String sql="SELECT * FROM zhangwu";
        Statement stmt;
        try {
            stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                Zhangwu zhangwu=new Zhangwu();
                //getInt --> int
                zhangwu.setZwid(rs.getInt("zwid"));
                //getString -->String
                zhangwu.setFlname(rs.getString("flname"));
                //getDouble -->double
                zhangwu.setMoney(rs.getDouble("money"));
                zhangwu.setZhanghu(rs.getString("zhanghu"));
                zhangwu.setCreatetime(rs.getString("createtime"));
                zhangwu.setDescription(rs.getString("description"));

                list.add(zhangwu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //将局部list_temp给list，好处是点击多个窗口，不会添加重复的的数据
    public List<Zhangwu> allData2(){
        List<Zhangwu> list_temp=new ArrayList<>();
        Connection conn=JDBCUtil.getConn();
        String sql="SELECT * FROM zhangwu";
        Statement stmt;
        try {
            stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                Zhangwu zhangwu=new Zhangwu();
                //getInt --> int
                zhangwu.setZwid(rs.getInt("zwid"));
                //getString -->String
                zhangwu.setFlname(rs.getString("flname"));
                //getDouble -->double
                zhangwu.setMoney(rs.getDouble("money"));
                zhangwu.setZhanghu(rs.getString("zhanghu"));
                zhangwu.setCreatetime(rs.getString("createtime"));
                zhangwu.setDescription(rs.getString("description"));

                list_temp.add(zhangwu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_temp;
    }


}
