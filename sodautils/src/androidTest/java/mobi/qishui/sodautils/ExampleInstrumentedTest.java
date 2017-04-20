package mobi.qishui.sodautils;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import mobi.qishui.sodautils.system.PackageUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("mobi.qishui.sodautils.test", appContext.getPackageName());
    }

    @Test
    public void testPackageUtil() {
        PackageUtil util = new PackageUtil();
        assertTrue(util.isPackageInstalled(InstrumentationRegistry.getTargetContext(), "com.tencent.mm"));
        assertFalse(util.isPackageInstalled(InstrumentationRegistry.getTargetContext(), "com.tencent.xx"));
        try {
            util.isPackageInstalled(InstrumentationRegistry.getTargetContext(), null);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "package name can't be empty");
        }
    }
}
