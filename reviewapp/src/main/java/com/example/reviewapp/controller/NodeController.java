package com.example.reviewapp.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import com.example.reviewapp.dto.NodeForm;
import com.example.reviewapp.entity.Node;
import com.example.reviewapp.entity.Category;
import com.example.reviewapp.service.AiService;
import com.example.reviewapp.service.NodeService;
import com.example.reviewapp.service.CategoryService;

@Controller
@RequestMapping("/nodes")
public class NodeController{

    //メンバ変数
    private final NodeService nodeService;
    private final AiService aiService;
    private final CategoryService categoryService;
    
    //コンストラクタ
    public NodeController(NodeService nodeService ,AiService aiService ,CategoryService categoryService){
        this.nodeService = nodeService;
        this.aiService = aiService;
        this.categoryService = categoryService;
    }



    //一覧を表示するメソッド
    @GetMapping
    public String findAll(Model model ,String keyword ,Long categoryId){
        List<Node> nodes = nodeService.findAll(keyword ,categoryId);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("nodes", nodes);
        model.addAttribute("keyword", keyword);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("categories", categories);
        return "nodes/list";
    }

    //新規画面を表示するメソッド
    @GetMapping("/new")
    public String newForm(Model model){
        model.addAttribute("nodeForm",new NodeForm());    
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories" ,categories);
        return "nodes/new";
    }

    //新規登録するメソッド
    //バリデーション（入力時空白の場合エラー表示）
    @PostMapping
    public String create(@Valid NodeForm nodeForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "nodes/new";
        }

        nodeService.create(nodeForm.getTitle(), nodeForm.getContent() ,nodeForm.getCategoryId() ,nodeForm.getReviewDate());
        return "redirect:/nodes";
    }

    //編集画面を表示するメソッド
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model){
        Node node = nodeService.findById(id);

        NodeForm nodeForm = new NodeForm();
        nodeForm.setTitle(node.getTitle());
        nodeForm.setContent(node.getContent());
        nodeForm.setReviewDate(node.getReviewDate());

         if (node.getCategory() != null) {
             nodeForm.setCategoryId(
                node.getCategory().getId()
            );
        }

        List<Category> categories = categoryService.findAll();

        model.addAttribute("nodeForm", nodeForm);
        model.addAttribute("nodeId",id);

        model.addAttribute("categories",categories);

        return "nodes/edit";
    }

    //復習日一覧表示メソッド
    @GetMapping("/review")
    public String reviewList(Model model){
        List<Node> reviewNodes = nodeService.findReviewNodes();
        model.addAttribute("nodes",reviewNodes);
        return "nodes/review";
    }

    //復習日を更新するメソッド
    @PostMapping("/{id}/reviewed")
    public String reviewed(@PathVariable Long id){
        nodeService.reviewed(id);
        return "redirect:/nodes/review";
    }

    //詳細画面
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id ,Model model){
        Node node = nodeService.findById(id);
        model.addAttribute("node",node);
        return "nodes/detail";
    }

    //編集するメソッド
    @PostMapping("/{id}")
    public String update(
            @PathVariable Long id, 
            @Valid NodeForm nodeForm,
            BindingResult bindingResult,
            Model model){

        if (bindingResult.hasErrors()){
            model.addAttribute("nodeId" ,id);
            return "nodes/edit";
        }

        nodeService.update(
            id, 
            nodeForm.getTitle(), 
            nodeForm.getContent(),
            nodeForm.getCategoryId(),
            nodeForm.getReviewDate()
        );
        return "redirect:/nodes";
    }

    //削除するメソッド
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        nodeService.delete(id);
        return "redirect:/nodes";
    }

    //AI補正メソッド
    @PostMapping("/{id}/ai-correct")
    public String aiCorrect(@PathVariable Long id ,NodeForm nodeForm ,Model model) {
        String correctedContent = aiService.correctContent(nodeForm.getContent());

        nodeForm.setContent(correctedContent);

        model.addAttribute("nodeForm", nodeForm);
        model.addAttribute("nodeId", id);

        return "nodes/edit";
    }


}