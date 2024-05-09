package com.nmt.universitysb.controller;

import com.nmt.universitysb.model.TuitionFee;
import com.nmt.universitysb.service.TuitionFeeService;
import com.nmt.universitysb.service.impl.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.web.servlet.ModelAndView;
import java.util.Date;

@RestController
@CrossOrigin
public class PaypalController {

    @Autowired
    PaypalService service;
    @Autowired
    private TuitionFeeService tuitionFeeService;

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";
    public static final String ERROR_URL = "pay/error";

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/pay")
    public String payment(Model model,
                          @RequestParam("tuitionFeeId") int tuitionFeeId) {
        TuitionFee tuitionFee = this.tuitionFeeService.findByTuitionFeeId(tuitionFeeId);
        double tuitionfeeTransfer = tuitionFee.getTuitionFee()/24985;
        model.addAttribute(tuitionFee);
        try {
            Payment payment = service.createPayment(tuitionfeeTransfer, "USD", "paypal",
                    "sale", "Pay tuition fee", "http://localhost:8082/" + CANCEL_URL,
                    "http://localhost:8082/" + SUCCESS_URL + "?tuitionFeeId=" + tuitionFeeId);
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return link.getHref();
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return "redirect:" + ERROR_URL;
    }

    @GetMapping(value = CANCEL_URL)
    public ModelAndView cancelPay() {
        return new ModelAndView("cancel");
    }

    @GetMapping(value = ERROR_URL)
    public ModelAndView errorPay() {
        return new ModelAndView("error");
    }

    @GetMapping(value = SUCCESS_URL)
    public ModelAndView successPay(@RequestParam("paymentId") String paymentId,
                             @RequestParam("PayerID") String payerId,
                             @RequestParam("tuitionFeeId") int tuitionFeeId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                TuitionFee tuitionFee = this.tuitionFeeService.findByTuitionFeeId(tuitionFeeId);
                tuitionFee.setDateCreated(new Date());
                tuitionFee.setDone(true);
                this.tuitionFeeService.save(tuitionFee);
                return new ModelAndView("success") ;
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return new ModelAndView("redirect:/");
    }

}
