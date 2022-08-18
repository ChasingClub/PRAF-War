package praf.server.main.events;

import com.destroystokyo.paper.event.entity.ProjectileCollideEvent;
import io.papermc.paper.event.entity.EntityMoveEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;
import org.spigotmc.event.entity.EntityDismountEvent;
import praf.server.main.Cuboid;

import static org.bukkit.Bukkit.getServer;
import static praf.server.main.PRAF.build;

public class cancelevent implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        if (!(build.contains(p.getName())))
            {e.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        if (!(build.contains(p.getName()))) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Material b = e.getClickedBlock().getType();
            Player p = e.getPlayer();
            if (b == Material.SPRUCE_TRAPDOOR) {
                if (!(build.contains(p.getName()))) {
                    e.setCancelled(true);
                    return;
                }
            } else if (b == Material.DARK_OAK_TRAPDOOR) {
                if (!(build.contains(p.getName()))) {
                    e.setCancelled(true);
                    return;
                }
            }
        }
    }
    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e){
        e.setCancelled(true);
    }
    @EventHandler
    public void FrameEntity(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof ItemFrame) {
            if (e.getDamager() instanceof Player) {
                Player pd = (Player) e.getDamager();
                if (!(build.contains(pd.getName()))) {
                    e.setCancelled(true);
                }
            }
            if (e.getDamager() instanceof Projectile) {
                if (((Projectile) e.getDamager()).getShooter() instanceof Player) {
                    Player pp = (Player) ((Projectile) e.getDamager()).getShooter();
                    if (!(build.contains(pp.getName()))) {
                        e.getDamager().remove();
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
    @EventHandler
    public void onInteractPot(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Block blk = e.getClickedBlock();
        if(blk == null) {
            return;
        }else if (blk.getType().name().startsWith("POTTED_") || blk.getType() == Material.FLOWER_POT) {
            if (!(build.contains(p.getName()))) {
                e.setCancelled(true);
                return;
            }
        }
    }
    @EventHandler
    public void Arrow(ProjectileHitEvent e) {
        if (e.getEntity() instanceof Arrow) {
            Arrow a = (Arrow) e.getEntity();
            a.remove();
        }
    }
    @EventHandler
    public void Trident(ProjectileLaunchEvent e){
        if (e.getEntity() instanceof Trident) {
            Player p = (Player) e.getEntity().getShooter();
            if (p == null){return;}
            Cuboid cuboid = new Cuboid(Bukkit.getServer().getWorld("world"), 160, 50, -66, -24, 2, 121);
          if (!(cuboid.contains(p.getLocation()))) {
              if (p.getWorld() == Bukkit.getServer().getWorld("world")){
                  e.setCancelled(true);
              }
          }
        }
    }
    @EventHandler
    public void TridentHit(ProjectileHitEvent e){
        if (e.getEntity() instanceof Trident) {
            Player p = (Player) e.getEntity().getShooter();
            if (p == null){return;}
            Cuboid cuboid = new Cuboid(Bukkit.getServer().getWorld("world"), 160, 50, -66, -24, 2, 121);
            if (!(cuboid.contains(p.getLocation()))) {
                if (p.getWorld() == Bukkit.getServer().getWorld("world")){
                    Trident t = (Trident) e.getEntity();
                    t.remove();
                }
            }
        }
    }
    @EventHandler
    public void TridentLaunch(ProjectileLaunchEvent e){
        if (e.getEntity() instanceof Trident) {
            Player p = (Player) e.getEntity().getShooter();
            if (p == null){return;}
            Cuboid cuboid = new Cuboid(Bukkit.getServer().getWorld("world"), 160, 50, -66, -24, 2, 121);
            if (!(cuboid.contains(p.getLocation()))) {
                if (p.getWorld() == Bukkit.getServer().getWorld("world")){
                    Trident t = (Trident) e.getEntity();
                    t.remove();
                }
            }
        }
    }
}
