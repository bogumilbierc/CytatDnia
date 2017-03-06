package jcrusader.pl.cytatdnia.service;

import android.util.Log;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import jcrusader.pl.cytatdnia.dto.QuoteDto;
import jcrusader.pl.cytatdnia.service.impl.QuoteServiceImpl;

/**
 * Created by bogumil on 3/6/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({QuoteServiceImpl.class , Log.class})
@PowerMockIgnore("javax.net.ssl.*")
public class QuoteServiceTest {

    QuoteService quoteService;

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(Log.class);
        PowerMockito.when(Log.w(Mockito.anyString(), Mockito.anyString())).thenReturn(1);
        quoteService = new QuoteServiceImpl();
    }

    @Test
    public void downloadQuoteForToday() throws Exception {

        QuoteDto quoteDto = quoteService.downloadQuoteForToday();
        Assert.assertEquals(true, true);
    }

}