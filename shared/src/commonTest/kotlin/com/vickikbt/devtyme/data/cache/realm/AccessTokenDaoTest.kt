package com.vickikbt.devtyme.data.cache.realm

import com.vickikbt.devtyme.data.cache.realm.models.AccessTokenEntity
import io.realm.Configuration
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlin.test.*

class AccessTokenDaoTest {

    lateinit var realmConfiguration: Configuration
    lateinit var realm: Realm
    lateinit var accessTokenDao: AccessTokenDao

    private val accessTokenEntity = AccessTokenEntity().apply {
        this.accessToken = ""
        this.expiresIn = 0.0
        this.refreshToken = ""
        this.scope = ""
        this.tokenType = ""
        this.uid = ""
        this.createdAt = 0f
    }

    @BeforeTest
    fun setup() {
        realmConfiguration = RealmConfiguration.Builder().name("test-realm").build()
        realm = Realm.open(realmConfiguration)
        accessTokenDao = AccessTokenDao(realm = realm)
    }

    @AfterTest
    fun tearDown() {
        realm.close()
    }

    @Test
    fun saveUserTokenTest() = runBlocking {
        assertNotNull(accessTokenEntity)

        accessTokenDao.saveToken(tokenEntity = accessTokenEntity)

        val accessToken = accessTokenDao.getToken.first()[0]

        assertNotNull(accessToken)
        assertEquals(accessTokenEntity, accessToken)
    }
}
