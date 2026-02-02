package com.lumber.inventory.ui.navigation;

/**
 * Defines navigation routes for the app.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \t2\u00020\u0001:\u000b\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011B\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\n\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u00a8\u0006\u001c"}, d2 = {"Lcom/lumber/inventory/ui/navigation/Screen;", "", "route", "", "(Ljava/lang/String;)V", "getRoute", "()Ljava/lang/String;", "AddLumber", "AddLumberWithMeasurements", "Companion", "EditLumber", "Filter", "Inventory", "Locations", "ReekonMeasurement", "Settings", "Setup", "Tags", "Lcom/lumber/inventory/ui/navigation/Screen$AddLumber;", "Lcom/lumber/inventory/ui/navigation/Screen$AddLumberWithMeasurements;", "Lcom/lumber/inventory/ui/navigation/Screen$EditLumber;", "Lcom/lumber/inventory/ui/navigation/Screen$Filter;", "Lcom/lumber/inventory/ui/navigation/Screen$Inventory;", "Lcom/lumber/inventory/ui/navigation/Screen$Locations;", "Lcom/lumber/inventory/ui/navigation/Screen$ReekonMeasurement;", "Lcom/lumber/inventory/ui/navigation/Screen$Settings;", "Lcom/lumber/inventory/ui/navigation/Screen$Setup;", "Lcom/lumber/inventory/ui/navigation/Screen$Tags;", "app_debug"})
public abstract class Screen {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String route = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LUMBER_ID_ARG = "lumberId";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LENGTH_ARG = "length";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String WIDTH_ARG = "width";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String THICKNESS_ARG = "thickness";
    @org.jetbrains.annotations.NotNull()
    public static final com.lumber.inventory.ui.navigation.Screen.Companion Companion = null;
    
    private Screen(java.lang.String route) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRoute() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/lumber/inventory/ui/navigation/Screen$AddLumber;", "Lcom/lumber/inventory/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class AddLumber extends com.lumber.inventory.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.lumber.inventory.ui.navigation.Screen.AddLumber INSTANCE = null;
        
        private AddLumber() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006\u00a8\u0006\t"}, d2 = {"Lcom/lumber/inventory/ui/navigation/Screen$AddLumberWithMeasurements;", "Lcom/lumber/inventory/ui/navigation/Screen;", "()V", "createRoute", "", "length", "", "width", "thickness", "app_debug"})
    public static final class AddLumberWithMeasurements extends com.lumber.inventory.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.lumber.inventory.ui.navigation.Screen.AddLumberWithMeasurements INSTANCE = null;
        
        private AddLumberWithMeasurements() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String createRoute(double length, double width, double thickness) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/lumber/inventory/ui/navigation/Screen$Companion;", "", "()V", "LENGTH_ARG", "", "LUMBER_ID_ARG", "THICKNESS_ARG", "WIDTH_ARG", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/lumber/inventory/ui/navigation/Screen$EditLumber;", "Lcom/lumber/inventory/ui/navigation/Screen;", "()V", "createRoute", "", "lumberId", "", "app_debug"})
    public static final class EditLumber extends com.lumber.inventory.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.lumber.inventory.ui.navigation.Screen.EditLumber INSTANCE = null;
        
        private EditLumber() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String createRoute(int lumberId) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/lumber/inventory/ui/navigation/Screen$Filter;", "Lcom/lumber/inventory/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class Filter extends com.lumber.inventory.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.lumber.inventory.ui.navigation.Screen.Filter INSTANCE = null;
        
        private Filter() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/lumber/inventory/ui/navigation/Screen$Inventory;", "Lcom/lumber/inventory/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class Inventory extends com.lumber.inventory.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.lumber.inventory.ui.navigation.Screen.Inventory INSTANCE = null;
        
        private Inventory() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/lumber/inventory/ui/navigation/Screen$Locations;", "Lcom/lumber/inventory/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class Locations extends com.lumber.inventory.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.lumber.inventory.ui.navigation.Screen.Locations INSTANCE = null;
        
        private Locations() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/lumber/inventory/ui/navigation/Screen$ReekonMeasurement;", "Lcom/lumber/inventory/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class ReekonMeasurement extends com.lumber.inventory.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.lumber.inventory.ui.navigation.Screen.ReekonMeasurement INSTANCE = null;
        
        private ReekonMeasurement() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/lumber/inventory/ui/navigation/Screen$Settings;", "Lcom/lumber/inventory/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class Settings extends com.lumber.inventory.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.lumber.inventory.ui.navigation.Screen.Settings INSTANCE = null;
        
        private Settings() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/lumber/inventory/ui/navigation/Screen$Setup;", "Lcom/lumber/inventory/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class Setup extends com.lumber.inventory.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.lumber.inventory.ui.navigation.Screen.Setup INSTANCE = null;
        
        private Setup() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/lumber/inventory/ui/navigation/Screen$Tags;", "Lcom/lumber/inventory/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class Tags extends com.lumber.inventory.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.lumber.inventory.ui.navigation.Screen.Tags INSTANCE = null;
        
        private Tags() {
        }
    }
}