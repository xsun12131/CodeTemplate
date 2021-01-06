package com.fatpanda.idea.right.ui;

import com.fatpanda.idea.right.util.GenerateUtil;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class ResultDialog extends ModuleWizardStep {
    private JPanel contentPane;
    private JTextField classNameValue;
    private JTextField packageNameValue;
    private JCheckBox controllerCheckBox;
    private JCheckBox serviceCheckBox;
    private JCheckBox serviceImplCheckBox;
    private JCheckBox repositoryCheckBox;
    private JTextField controllerValue;
    private JTextField serviceValue;
    private JTextField serviceImplValue;
    private JTextField repositoryValue;
    private JCheckBox suffixShow;
    private JPanel suffixPanel;

    private Project myProject;
    private PsiClass myClass;
    private Map<String, Object> stringObjectMap;

    public ResultDialog(Project project, PsiClass clazz) {
        myProject = project;
        myClass = clazz;
        initComBox();
        bind();
    }

    private void bind() {
        controllerCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigData.setSelectController(controllerCheckBox.isSelected());
            }
        });
        serviceCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigData.setSelectedService(serviceCheckBox.isSelected());
            }
        });
        serviceImplCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigData.setSelectedServiceImpl(serviceImplCheckBox.isSelected());
            }
        });
        repositoryCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigData.setSelectedRepository(repositoryCheckBox.isSelected());
            }
        });
        suffixShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(suffixShow.isSelected()) {
                    suffixPanel.setVisible(true);
                } else {
                    suffixPanel.setVisible(false);
                }
            }
        });
        packageNameValue.addActionListener(actionListener -> {
            ConfigData.setPackageName(packageNameValue.getText());
        });

    }

    @Override
    public JComponent getComponent() {
        return contentPane;
    }

    private void initComBox() {
        stringObjectMap = GenerateUtil.getEntityProperty();
        classNameValue.setText(stringObjectMap.get("entityName").toString());
        packageNameValue.setText(stringObjectMap.get("parentPackageName").toString());
        controllerValue.setText(ConfigData.getControllerSuffix());
        serviceValue.setText(ConfigData.getServiceSuffix());
        serviceImplValue.setText(ConfigData.getServiceImplSuffix());
        repositoryValue.setText(ConfigData.getRepositorySuffix());
        suffixPanel.setVisible(false);
    }

    @Override
    public void updateDataModel() {

    }

    public void afterOk() {
        ConfigData.setPackageName(packageNameValue.getText());
    }
}
