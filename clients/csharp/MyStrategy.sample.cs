//#undef DEBUG // раскомментируй, когда запускаешь repeater
using Com.CodeGame.CodeWizards2016.DevKit.CSharpCgdk.Model;

namespace Com.CodeGame.CodeWizards2016.DevKit.CSharpCgdk {
    public sealed class MyStrategy : IStrategy {
#if DEBUG // убрать код визуализатора в release версии для сервера
		// инициализировать один раз объект VisualClient
        private VisualClient vc = new VisualClient("127.0.0.1", 13579);
#endif
        public void Move(Wizard self, World world, Game game, Move move) {
#if DEBUG
			// один раз вызвать Begin*()
            vc.BeginPost();
#endif
            move.Speed = game.WizardForwardSpeed;
            move.StrafeSpeed = game.WizardStrafeSpeed;
            move.Turn = game.WizardMaxTurnAngle;
            move.Action = ActionType.MagicMissile;
#if DEBUG
			// вывод отладочной информации
            vc.Text(self.X - 35, self.Y - 37, string.Format("{0:D} | {1:F0} : {2:F0}", self.RemainingCooldownTicksByAction[(int)ActionType.MagicMissile], self.X, self.Y));
            vc.Line(self.X, self.Y, self.X + Math.Cos(self.Angle - game.StaffSector / 2f)*self.CastRange, self.Y + Math.Sin(self.Angle - game.StaffSector / 2f)*self.CastRange, 1f, 0.7f, 0.7f);
            vc.Line(self.X, self.Y, self.X + Math.Cos(self.Angle + game.StaffSector / 2f) * self.CastRange, self.Y + Math.Sin(self.Angle + game.StaffSector / 2f) * self.CastRange, 1f, 0.7f, 0.7f);
            vc.Arc(self.X, self.Y, self.CastRange, self.Angle - game.StaffSector / 2f, game.StaffSector, 1f, 0.7f, 0.7f);
#endif
            move.Turn = 0.01;
            move.Speed = 3;
            move.Action = ActionType.MagicMissile;
#if DEBUG
			// в конце вызвать End*()
            vc.EndPost();
#endif
        }
    }
}