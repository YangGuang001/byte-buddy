package net.bytebuddy.build;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.type.TypeDescription;
import org.junit.Before;
import org.junit.Test;

public class CachedReturnPluginErrorTest {

    private Plugin plugin;

    @Before
    public void setUp() throws Exception {
        plugin = new CachedReturnPlugin();
    }

    @Test(expected = IllegalStateException.class)
    public void testCacheVoid() {
        plugin.apply(new ByteBuddy().redefine(VoidCache.class), TypeDescription.ForLoadedType.of(VoidCache.class));
    }

    @Test(expected = IllegalStateException.class)
    public void testAbstractMethod() {
        plugin.apply(new ByteBuddy().redefine(AbstractCache.class), TypeDescription.ForLoadedType.of(AbstractCache.class));
    }

    @Test(expected = IllegalStateException.class)
    public void testParameterMethod() {
        plugin.apply(new ByteBuddy().redefine(ParameterCache.class), TypeDescription.ForLoadedType.of(ParameterCache.class));
    }

    private static class VoidCache {

        @CachedReturnPlugin.Enhance
        private void foo() {
            /* do nothing */
        }
    }

    private abstract static class AbstractCache {

        @CachedReturnPlugin.Enhance
        protected abstract void foo();
    }

    private static class ParameterCache {

        @CachedReturnPlugin.Enhance
        private void foo(Void argument) {
            /* do nothing */
        }
    }
}