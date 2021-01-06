package com.fatpanda.idea.right;

import com.fatpanda.idea.right.ui.ConfigData;
import com.fatpanda.idea.right.ui.ConfigDialog;
import com.fatpanda.idea.right.util.GenerateUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.project.Project;
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
        GenerateUtil.generateProperty(clazz);

        Project project = e.getProject();
        ConfigData.clearSet();
        ConfigDialog dialog = new ConfigDialog(project,clazz);
        if (!dialog.showAndGet()) {
            return;
        }

        createTemplate(clazz);

        VirtualFileManager manager = VirtualFileManager.getInstance();
        manager.syncRefresh();

    }

    private void createTemplate(PsiClass clazz) {
        Map<String, Object> entityMap = GenerateUtil.getEntityProperty();

        if(ConfigData.getSelectController()) {
            try {
                GenerateUtil.generateTemplate(clazz, TemplateType.CONTROLLER);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        if(ConfigData.getSelectedService()) {
            try {
                GenerateUtil.generateTemplate(clazz, TemplateType.SERVICE);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        if(ConfigData.getSelectedServiceImpl()) {
            try {
                GenerateUtil.generateTemplate(clazz, TemplateType.SERVICEIMPL);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        if(ConfigData.getSelectedRepository()) {
            try {
                GenerateUtil.generateTemplate(clazz, TemplateType.REPOSITORY);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
