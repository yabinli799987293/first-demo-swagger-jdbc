package com.example.firstdemoswaggerjdbc.service;

import com.example.firstdemoswaggerjdbc.repostory.ZwRepostory;
import com.example.firstdemoswaggerjdbc.table.Zhangwu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ZwService {
    @Autowired
    ZwRepostory zwRepostory;
    /**
     * 添加账务
     * @param zhangwu
     * @return
     */
    public List<Zhangwu> add(Zhangwu zhangwu){

        return zwRepostory.add(zhangwu);
    }

    public List<Zhangwu> add(String flname,double money,String zhanghu,String createtime,String description){

        Zhangwu zhangwu=new Zhangwu(0,flname,money,zhanghu,createtime,description);
        List<Zhangwu> list=zwRepostory.add(zhangwu);
        return list;
    }

    public String add2(String flname,double money,String zhanghu,String createtime,String description){

        Zhangwu zhangwu=new Zhangwu(0,flname,money,zhanghu,createtime,description);
        Zhangwu list_zhangwu=zwRepostory.add2(zhangwu);

        return list_zhangwu.toString()+"\n添加成功";
    }



    /**
     * 编辑账务
     * @param zhangwu
     */
    public String edit(Zhangwu zhangwu){

        return zwRepostory.edit(zhangwu);
    }

    public String edit(int zwid,String flname,double money,String zhanghu,String createtime,String description){
        return zwRepostory.edit(zwid,flname,money,zhanghu,createtime,description);
    }

    /**
     * 删除账务
     * @param
     */
    public String delete(int zwid){

        //delete2
        return zwRepostory.delete2(zwid);
    }

    /**
     * 查询账务
     * @param flname
     * @return
     */
    public List<Zhangwu> searchOfName(String flname){

        return zwRepostory.searchOfName(flname);
    }

    public Zhangwu searchOfZwid(int zwid){

        return zwRepostory.searchOfZwid(zwid);
    }

    public List<Zhangwu> searchOfTime(String createtime){

        return zwRepostory.searchOfTime(createtime);
    }

    public List<Zhangwu> searchAll(){

        return zwRepostory.searchAll();
    }



}
