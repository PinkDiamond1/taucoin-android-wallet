package com.mofei.tau.src.com.google.bitcoin.bouncycastle.crypto.params;

import java.security.SecureRandom;

import com.mofei.tau.src.com.google.bitcoin.bouncycastle.crypto.KeyGenerationParameters;

public class ECKeyGenerationParameters
    extends KeyGenerationParameters
{
    private ECDomainParameters  domainParams;

    public ECKeyGenerationParameters(
        ECDomainParameters      domainParams,
        SecureRandom            random)
    {
        super(random, domainParams.getN().bitLength());

        this.domainParams = domainParams;
    }

    public ECDomainParameters getDomainParameters()
    {
        return domainParams;
    }
}