package com.fatpanda.idea.right;

import com.fatpanda.idea.right.ui.ConfigDialog;
import com.fatpanda.idea.right.util.GenerateUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
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
        Project project = e.getProject();
        ConfigDialog dialog = new ConfigDialog(project,clazz);
        if (!dialog.showAndGet()) {
            return;
        }

        VirtualFileManager manager = VirtualFileManager.getInstance();
        manager.syncRefresh();


    }
}
