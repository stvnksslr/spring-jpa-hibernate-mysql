package com.skessler.controller;

import com.skessler.exception.OrderNotFound;
import com.skessler.model.Order;
import com.skessler.service.OrderService;
import com.skessler.validation.OrderValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value="/order")
public class AcsOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderValidator orderValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(orderValidator);
    }

    @RequestMapping(value="/create", method= RequestMethod.GET)
    public ModelAndView newOrderPage() {
        ModelAndView mav = new ModelAndView("order-new", "order", new Order());
        return mav;
    }

    @RequestMapping(value="/create", method= RequestMethod.POST)
    public ModelAndView createNewOrder(@ModelAttribute @Valid Order order,
                                      BindingResult result,
                                      final RedirectAttributes redirectAttributes) {

        if (result.hasErrors())
            return new ModelAndView("order-new");

        ModelAndView mav = new ModelAndView();
        String message = "New order "+order.getOrderdata()+" was successfully created.";

        orderService.create(order);
        mav.setViewName("redirect:/index.html");

        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value="/list", method= RequestMethod.GET)
    public ModelAndView orderListPage() {
        ModelAndView mav = new ModelAndView("order-list");
        List<Order> orderList = orderService.findAll();
        mav.addObject("orderList", orderList);
        return mav;
    }

    @RequestMapping(value="/edit/{id}", method= RequestMethod.GET)
    public ModelAndView editOrderPage(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("order-edit");
        Order order = orderService.findById(id);
        mav.addObject("order", order);
        return mav;
    }

    @RequestMapping(value="/edit/{id}", method= RequestMethod.POST)
    public ModelAndView editOrder(@ModelAttribute @Valid Order order,
                                 BindingResult result,
                                 @PathVariable Integer id,
                                 final RedirectAttributes redirectAttributes) throws OrderNotFound {

        if (result.hasErrors())
            return new ModelAndView("order-edit");

        ModelAndView mav = new ModelAndView("redirect:/index.html");
        String message = "Order was successfully updated.";

        orderService.update(order);

        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value="/delete/{id}", method= RequestMethod.GET)
    public ModelAndView deleteOrder(@PathVariable Integer id,
                                   final RedirectAttributes redirectAttributes) throws OrderNotFound {

        ModelAndView mav = new ModelAndView("redirect:/index.html");

        Order order = orderService.delete(id);
        String message = "The order "+order.getOrderdata()+" was successfully deleted.";

        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }


}
