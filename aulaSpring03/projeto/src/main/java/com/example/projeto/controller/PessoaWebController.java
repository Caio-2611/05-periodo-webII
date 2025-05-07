package com.example.projeto.controller;

import.jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindinResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.projeto.model.Pessoa;
import com.example.projeto.service.PessoaService;

import org.springframework.http.HttpStatus;
import com.example.projeto.model.Pessoa;

import com.example.projeto.service.PessoaService;

@Controller
@RequestMapping("/pessoas")
public class PessoaWebController{

    private final PessoaService pessoaService;

    public PessoaWebController(PessoaService pessoaService){
        this.pessoaService = pessoaService;
    }

    //Mapeia Get/pessoas -> redireciona para pessoas/listar
    @GetMapping
    public String index(){
        return "redirect:/pessoas/listar";
    }

    //Pagina Cadastro
    @GetMapping("/cadastrar")
    public String exibirFormCadastro(Model model){
        model.addAttribute("pessoa",new Pessoa());
        return "pessoas/form";
    }

    @PostMapping("/cadastrar")
    public String cadastrarPessoa(
        @Valid @ModelAttribute("pessoa") Pessoa 
        pessoa, BindinResult result,
            RedirectAttributes ra){
            if(result.hasErros()){
                //repopula o objeo no formulario em caso de erro
                return "pessoa/form";
            } 
            pessoaService.salvarPessoa(pessoa);
            ra.addFlashAttribute("sucesso", "Pessoa cadastrada com sucesso!");
            return "redirect:/pessoas/listar";
            }
    //Pagina listagem
    @GetMapping("/listar")
    public String listarPessoas(Model model){
        model.addAttribute("listar", pessoaService.listarPessoas());
        return "pessoas/lista";
    }

    //Detalhes/Exclusao
    @GetMapping("/{id}")
    public String detalhesPessoa(@PathVariable Long id, Model model){
        Pessoa p = pessoaService.buscarPorId(id)
        .orElseThrow(() -> new ResponseStatusExcepion(
            HttpStatus.NOT_FOUND,"Pessoa não encontrada, id:" + id
        ));
        model.addAttribute("pessoa", p);
        return "pessoas/detalhe";
    }

    public String excluirPessoa(@PathVariable Long id, RedirectAttributes ra){
        pessoaService.deletarPessoa(id);
        ra.addAttribute("sucesso", "Pessoa excluída com sucesso");
        return "redirect:/pessoas/listar";
    }
}