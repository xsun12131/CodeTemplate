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

    private static Map<String, Object> stringObjectMap;

    public ResultDialog(Project project, PsiClass clazz) {
        initComBox();
        bind();
    }

    private void bind() {
        controllerCheckBox.addActionListener(e -> ConfigData.setSelectController(controllerCheckBox.isSelected()));
        serviceCheckBox.addActionListener(e -> ConfigData.setSelectedService(serviceCheckBox.isSelected()));
        serviceImplCheckBox.addActionListener(e -> ConfigData.setSelectedServiceImpl(serviceImplCheckBox.isSelected()));
        repositoryCheckBox.addActionListener(e -> ConfigData.setSelectedRepository(repositoryCheckBox.isSelected()));
        suffixShow.addActionListener(e -> {
            if(suffixShow.isSelected()) {
                suffixPanel.setVisible(true);
            } else {
                suffixPanel.setVisible(false);
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
