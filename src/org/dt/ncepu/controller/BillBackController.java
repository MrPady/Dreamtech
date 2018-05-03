package org.dt.ncepu.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dt.ncepu.domain.Bill;
import org.dt.ncepu.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.dt.ncepu.domain.Bill.setAllStringTime;

/**
 * Created by Administrator on 2017/08/23 0023.
 */
@Controller
public class BillBackController {
    private static final Log loggers =
            LogFactory.getLog(BillBackController.class);

    @Autowired
    private BillService billService;

    @RequestMapping(value = "/showBills", method = {RequestMethod.GET, RequestMethod.POST})
    public String showBill(Model model) {
        model.addAttribute("bills",setAllStringTime(billService.showBills()));
        return "/BillList";
    }

    @RequestMapping(value = "/addBill", method = {RequestMethod.GET, RequestMethod.POST})
    public String addBill(Model model) {
        model.addAttribute("bill",new Bill());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.addAttribute("time",sdf.format(new Date(System.currentTimeMillis())));
        return "/BillForm";
    }


    @RequestMapping(value = "/saveBill", method = {RequestMethod.GET, RequestMethod.POST})
    public String saveBill(Bill bill,Model model) {
        if(bill.setTime()){
            billService.addBill(bill);
            return "redirect:/showBills";
        }
        model.addAttribute("Error","日期格式有误");
        return "/BillForm";
    }

    @RequestMapping(value = "/deleteBill/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteBill(@PathVariable int id) {
        billService.deleteBill(id);
        return "redirect:/showBills";
    }

}
