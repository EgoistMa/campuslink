package com.shiropure.campuslink.repository.customInterface;

import com.shiropure.campuslink.entity.UserAssets;

import java.util.UUID;

public interface CustomUserAssetsRepository {
    public UserAssets getUserAssetsFromUuid(UUID uuid);
}
