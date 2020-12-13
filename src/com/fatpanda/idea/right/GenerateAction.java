package com.fatpanda.idea.right;

import com.fatpanda.idea.right.util.GenerateUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;

/**
 * @Author: fatpanda
 * @Date: 2020/12/11
 */
public class GenerateAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        // 获取当前编辑的文件, 通过PsiFile可获得PsiClass, PsiField等对象
        PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);
        //获取Java类的class文件
        PsiClass clazz = PsiTreeUtil.findChildOfAnyType(psiFile, PsiClass.class);
        Map<String, Object> entityMap = GenerateUtil.generateProperty(clazz);
        try {
            GenerateUtil.generateTemplate(entityMap, clazz, TemplateType.CONTROLLER);
            GenerateUtil.generateTemplate(entityMap, clazz, TemplateType.SERVICE);
            GenerateUtil.generateTemplate(entityMap, clazz, TemplateType.SERVICEIMPL);
            GenerateUtil.generateTemplate(entityMap, clazz, TemplateType.REPOSITORY);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        VirtualFileManager manager = VirtualFileManager.getInstance();
        manager.syncRefresh();


    }
}
