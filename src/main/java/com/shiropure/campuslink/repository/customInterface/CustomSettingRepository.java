package com.shiropure.campuslink.repository.customInterface;

import com.shiropure.campuslink.entity.Setting;

import java.util.Optional;

public interface CustomSettingRepository {
    public Optional<Setting> findByUUid(String uuid);
}
