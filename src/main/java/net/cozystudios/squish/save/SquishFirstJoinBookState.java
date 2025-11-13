package net.cozystudios.squish.save;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SquishFirstJoinBookState extends PersistentState {
    private static final String KEY = "squish_first_join_book";
    private static final String TAG = "gifted";
    private final Set<UUID> gifted = new HashSet<>();

    public static SquishFirstJoinBookState get(MinecraftServer server) {
        PersistentStateManager mgr = server.getOverworld().getPersistentStateManager();
        return mgr.getOrCreate(SquishFirstJoinBookState::fromNbt, SquishFirstJoinBookState::new, KEY);
    }

    public boolean markIfNew(UUID uuid) {
        boolean added = gifted.add(uuid);
        if (added) markDirty();
        return added;
    }

    public static SquishFirstJoinBookState fromNbt(NbtCompound nbt) {
        SquishFirstJoinBookState state = new SquishFirstJoinBookState();
        NbtList list = nbt.getList(TAG, NbtElement.STRING_TYPE);
        for (int i = 0; i < list.size(); i++) {
            state.gifted.add(UUID.fromString(list.getString(i)));
        }
        return state;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        NbtList list = new NbtList();
        for (UUID id : gifted) list.add(NbtString.of(id.toString()));
        nbt.put(TAG, list);
        return nbt;
    }
}