/*
 * ATLauncher - https://github.com/ATLauncher/ATLauncher
 * Copyright (C) 2013-2022 ATLauncher
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.atlauncher.gui.tabs.settings;

import java.awt.GridBagConstraints;
import java.awt.event.ItemEvent;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import org.mini2Dx.gettext.GetText;

import com.atlauncher.App;
import com.atlauncher.builders.HTMLBuilder;
import com.atlauncher.constants.UIConstants;
import com.atlauncher.data.AddModRestriction;
import com.atlauncher.data.InstanceExportFormat;
import com.atlauncher.data.ModPlatform;
import com.atlauncher.gui.components.JLabelWithHover;
import com.atlauncher.utils.ComboItem;
import com.atlauncher.viewmodel.impl.settings.ModsSettingsViewModel;

public class ModsSettingsTab extends AbstractSettingsTab {
    private final ModsSettingsViewModel viewModel;

    public ModsSettingsTab(ModsSettingsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    protected void onShow() {
        // Default mod platform

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = UIConstants.LABEL_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;

        JLabelWithHover defaultModPlatformLabel = new JLabelWithHover(GetText.tr("Default Mod Platform") + ":",
                HELP_ICON, GetText.tr(
                        "The default mod platform to use when adding mods to instances, as well as the platform to use when updating/reinstalling mods on multiple platforms."));

        add(defaultModPlatformLabel, gbc);

        gbc.gridx++;
        gbc.insets = UIConstants.FIELD_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        JComboBox<ComboItem<ModPlatform>> defaultModPlatform = new JComboBox<>();
        defaultModPlatform.addItem(new ComboItem<>(ModPlatform.CURSEFORGE, "CurseForge"));
        defaultModPlatform.addItem(new ComboItem<>(ModPlatform.MODRINTH, "Modrinth"));

        defaultModPlatform.addItemListener(itemEvent -> {
            if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                @SuppressWarnings("unchecked")
                ComboItem<ModPlatform> item = (ComboItem<ModPlatform>) itemEvent.getItem();

                viewModel.setDefaultModPlatform(item.getValue());
            }
        });
        addDisposable(viewModel.getDefaultModPlatform().subscribe(defaultModPlatform::setSelectedIndex));

        add(defaultModPlatform, gbc);

        // Default export format

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = UIConstants.LABEL_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;

        JLabelWithHover defaultExportFormatLabel = new JLabelWithHover(GetText.tr("Default Export Format") + ":",
                HELP_ICON, GetText.tr(
                        "The default format to export instances to. Can also be changed at time of export."));

        add(defaultExportFormatLabel, gbc);

        gbc.gridx++;
        gbc.insets = UIConstants.FIELD_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        JComboBox<ComboItem<InstanceExportFormat>> defaultExportFormat = new JComboBox<>();
        defaultExportFormat.addItem(new ComboItem<>(InstanceExportFormat.CURSEFORGE, "CurseForge"));
        defaultExportFormat.addItem(new ComboItem<>(InstanceExportFormat.MODRINTH, "Modrinth"));
        defaultExportFormat
                .addItem(new ComboItem<>(InstanceExportFormat.CURSEFORGE_AND_MODRINTH, "CurseForge & Modrinth"));
        defaultExportFormat.addItem(new ComboItem<>(InstanceExportFormat.MULTIMC, "MultiMC"));

        defaultExportFormat.addItemListener(itemEvent -> {
            if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                @SuppressWarnings("unchecked")
                ComboItem<InstanceExportFormat> item = (ComboItem<InstanceExportFormat>) itemEvent.getItem();

                viewModel.setDefaultExportFormat(item.getValue());
            }
        });
        addDisposable(viewModel.getDefaultExportFormat().subscribe(defaultExportFormat::setSelectedIndex));

        add(defaultExportFormat, gbc);

        // Add Mod Restrictions

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = UIConstants.LABEL_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;

        JLabelWithHover addModRestrictionsLabel = new JLabelWithHover(GetText.tr("Add Mod Restrictions") + ":",
                HELP_ICON, GetText.tr("What restrictions should be in place when adding mods from a mod platform."));

        add(addModRestrictionsLabel, gbc);

        gbc.gridx++;
        gbc.insets = UIConstants.FIELD_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        JComboBox<ComboItem<AddModRestriction>> addModRestriction = new JComboBox<>();
        addModRestriction.addItem(
                new ComboItem<>(AddModRestriction.STRICT, GetText.tr("Only show mods for current Minecraft version")));
        addModRestriction.addItem(new ComboItem<>(AddModRestriction.LAX,
                GetText.tr("Show mods for the current major Minecraft version (eg: 1.16.x)")));
        addModRestriction
                .addItem(new ComboItem<>(AddModRestriction.NONE, GetText.tr("Show mods for all Minecraft versions")));

        addModRestriction.addItemListener(itemEvent -> {
            if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                @SuppressWarnings("unchecked")
                ComboItem<AddModRestriction> item = (ComboItem<AddModRestriction>) itemEvent.getItem();

                viewModel.setAddModRestrictions(item.getValue());
            }
        });
        addDisposable(viewModel.getAddModRestriction().subscribe(addModRestriction::setSelectedIndex));

        add(addModRestriction, gbc);

        // Enable added mods by default

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = UIConstants.LABEL_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        JLabelWithHover enableAddedModsByDefaultLabel = new JLabelWithHover(GetText.tr("Enable Added Mods By Default?"),
                HELP_ICON, new HTMLBuilder().center().split(100)
                        .text(GetText.tr("When adding mods manually, should they be enabled automatically?")).build());
        add(enableAddedModsByDefaultLabel, gbc);

        gbc.gridx++;
        gbc.insets = UIConstants.CHECKBOX_FIELD_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        JCheckBox enableAddedModsByDefault = new JCheckBox();
        enableAddedModsByDefault.addItemListener(
                itemEvent -> viewModel.setEnableAddedModsByDefault(itemEvent.getStateChange() == ItemEvent.SELECTED));
        addDisposable(viewModel.getEnableAddedModsByDefault().subscribe(enableAddedModsByDefault::setSelected));
        add(enableAddedModsByDefault, gbc);

        // Show Fabric Mods When Sinytra Installed

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = UIConstants.LABEL_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        JLabelWithHover showFabricModsWhenSinytraInstalledLabel = new JLabelWithHover(
                GetText.tr("Show Fabric Mods When Sinytra Installed?"),
                HELP_ICON, new HTMLBuilder().center().split(100)
                        .text(GetText.tr("When Sinytra Connector is installed, should Fabric mods be shown?")).build());
        add(showFabricModsWhenSinytraInstalledLabel, gbc);

        gbc.gridx++;
        gbc.insets = UIConstants.CHECKBOX_FIELD_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        JCheckBox showFabricModsWhenSinytraInstalled = new JCheckBox();
        showFabricModsWhenSinytraInstalled.addItemListener(itemEvent -> viewModel
                .setShowFabricModsWhenSinytraInstalled(itemEvent.getStateChange() == ItemEvent.SELECTED));
        addDisposable(viewModel.getShowFabricModsWhenSinytraInstalled()
                .subscribe(showFabricModsWhenSinytraInstalled::setSelected));
        add(showFabricModsWhenSinytraInstalled, gbc);

        // Allow CurseForge Alpha/Beta CurseForge files

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = UIConstants.LABEL_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        JLabelWithHover allowCurseForgeAlphaBetaFilesLabel = new JLabelWithHover(
                GetText.tr("Allow CurseForge Alpha/Beta Files?"),
                HELP_ICON, new HTMLBuilder().center().split(100)
                        .text(GetText.tr(
                                "This will enable using Alpha/Beta files from CurseForge by default when installing modpacks as well as updating to Alpha/Beta versions from stable release versions."))
                        .build());
        add(allowCurseForgeAlphaBetaFilesLabel, gbc);

        gbc.gridx++;
        gbc.insets = UIConstants.CHECKBOX_FIELD_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        JCheckBox allowCurseForgeAlphaBetaFiles = new JCheckBox();
        allowCurseForgeAlphaBetaFiles.setSelected(App.settings.allowCurseForgeAlphaBetaFiles);
        allowCurseForgeAlphaBetaFiles.addItemListener(itemEvent -> viewModel
                .setAllowCurseForgeAlphaBetaFiles(itemEvent.getStateChange() == ItemEvent.SELECTED));
        addDisposable(
                viewModel.getAllowCurseForgeAlphaBetaFiles().subscribe(allowCurseForgeAlphaBetaFiles::setSelected));
        add(allowCurseForgeAlphaBetaFiles, gbc);

        // Dont check mods on CurseForge

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = UIConstants.LABEL_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        JLabelWithHover dontCheckModsOnCurseForgeLabel = new JLabelWithHover(
                // #. {0} is the platform (e.g. CurseForge/Modrinth)
                GetText.tr("Don't Check Mods On {0}?", "CurseForge"), HELP_ICON,
                // #. {0} is the platform (e.g. CurseForge/Modrinth)
                new HTMLBuilder().center().split(100).text(GetText.tr(
                        "When installing packs or adding mods manually to instances, we check for the file on {0} to show more information about the mod as well as make updating easier. Disabling this will mean you won't be able to update manually added mods from within the launcher.",
                        "CurseForge"))
                        .build());
        add(dontCheckModsOnCurseForgeLabel, gbc);

        gbc.gridx++;
        gbc.insets = UIConstants.CHECKBOX_FIELD_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        JCheckBox dontCheckModsOnCurseForge = new JCheckBox();
        dontCheckModsOnCurseForge.addItemListener(
                itemEvent -> viewModel.setDoNotCheckModsOnCurseForge(itemEvent.getStateChange() == ItemEvent.SELECTED));
        addDisposable(viewModel.getDoNotCheckModsOnCurseForge().subscribe(dontCheckModsOnCurseForge::setSelected));
        add(dontCheckModsOnCurseForge, gbc);

        // Dont check mods on Modrinth

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = UIConstants.LABEL_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        JLabelWithHover dontCheckModsOnModrinthLabel = new JLabelWithHover(
                // #. {0} is the platform (e.g. CurseForge/Modrinth)
                GetText.tr("Don't Check Mods On {0}?", "Modrinth"), HELP_ICON,
                // #. {0} is the platform (e.g. CurseForge/Modrinth)
                new HTMLBuilder().center().split(100).text(GetText.tr(
                        "When installing packs or adding mods manually to instances, we check for the file on {0} to show more information about the mod as well as make updating easier. Disabling this will mean you won't be able to update manually added mods from within the launcher.",
                        "Modrinth"))
                        .build());
        add(dontCheckModsOnModrinthLabel, gbc);

        gbc.gridx++;
        gbc.insets = UIConstants.CHECKBOX_FIELD_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        JCheckBox dontCheckModsOnModrinth = new JCheckBox();
        dontCheckModsOnModrinth.addItemListener(
                itemEvent -> viewModel.setDoNotCheckModsOnModrinth(itemEvent.getStateChange() == ItemEvent.SELECTED));
        addDisposable(viewModel.getDoNotCheckModsOnModrinth().subscribe(dontCheckModsOnModrinth::setSelected));
        add(dontCheckModsOnModrinth, gbc);

        // Enable scanning mods on launch
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = UIConstants.LABEL_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        JLabelWithHover scanModsOnLaunchLabel = new JLabelWithHover(GetText.tr("Scan Mods On Launch?"), HELP_ICON,
                new HTMLBuilder().center().split(100)
                        .text(GetText.tr(
                                "This will scan the mods in instances before launching to ensure they do not contain malware or have been modified since installing."))
                        .build());
        add(scanModsOnLaunchLabel, gbc);

        gbc.gridx++;
        gbc.insets = UIConstants.CHECKBOX_FIELD_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        JCheckBox scanModsOnLaunch = new JCheckBox();
        scanModsOnLaunch.setSelected(App.settings.scanModsOnLaunch);
        scanModsOnLaunch.addItemListener(e -> viewModel.setScanModsOnLaunch(e.getStateChange() == ItemEvent.SELECTED));
        addDisposable(viewModel.getScanModsOnLaunch().subscribe(scanModsOnLaunch::setSelected));
        add(scanModsOnLaunch, gbc);
    }

    @Override
    public String getTitle() {
        return GetText.tr("Mods");
    }

    @Override
    public String getAnalyticsScreenViewName() {
        return "Mods";
    }

    @Override
    protected void createViewModel() {}

    @Override
    protected void onDestroy() {
        removeAll();
    }
}
